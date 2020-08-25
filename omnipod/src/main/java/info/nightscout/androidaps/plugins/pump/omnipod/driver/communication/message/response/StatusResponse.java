package info.nightscout.androidaps.plugins.pump.omnipod.driver.communication.message.response;

import org.joda.time.Duration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import info.nightscout.androidaps.plugins.pump.common.utils.ByteUtil;
import info.nightscout.androidaps.plugins.pump.omnipod.driver.communication.message.MessageBlock;
import info.nightscout.androidaps.plugins.pump.omnipod.driver.definition.AlertSet;
import info.nightscout.androidaps.plugins.pump.omnipod.driver.definition.DeliveryStatus;
import info.nightscout.androidaps.plugins.pump.omnipod.driver.definition.MessageBlockType;
import info.nightscout.androidaps.plugins.pump.omnipod.driver.definition.OmnipodConstants;
import info.nightscout.androidaps.plugins.pump.omnipod.driver.definition.PodProgressStatus;

public class StatusResponse extends MessageBlock {
    private static final int MESSAGE_LENGTH = 10;

    private final DeliveryStatus deliveryStatus;
    private final PodProgressStatus podProgressStatus;
    private final Duration timeActive;
    private final Double reservoirLevel;
    private final int ticksDelivered;
    private final double insulinDelivered;
    private final double insulinNotDelivered;
    private final byte podMessageCounter;
    private final AlertSet alerts;

    public StatusResponse(byte[] encodedData) {
        if (encodedData.length < MESSAGE_LENGTH) {
            throw new IllegalArgumentException("Not enough data");
        }
        this.encodedData = ByteUtil.substring(encodedData, 1, MESSAGE_LENGTH - 1);

        deliveryStatus = DeliveryStatus.fromByte((byte) (ByteUtil.convertUnsignedByteToInt(encodedData[1]) >>> 4));
        podProgressStatus = PodProgressStatus.fromByte((byte) (encodedData[1] & 0x0F));

        int minutes = ((encodedData[7] & 0x7F) << 6) | ((encodedData[8] & 0xFC) >>> 2);
        timeActive = Duration.standardMinutes(minutes);

        int highInsulinBits = (encodedData[2] & 0xF) << 9;
        int middleInsulinBits = ByteUtil.convertUnsignedByteToInt(encodedData[3]) << 1;
        int lowInsulinBits = ByteUtil.convertUnsignedByteToInt(encodedData[4]) >>> 7;
        ticksDelivered = (highInsulinBits | middleInsulinBits | lowInsulinBits);
        insulinDelivered = OmnipodConstants.POD_PULSE_SIZE * ticksDelivered;
        podMessageCounter = (byte) ((encodedData[4] >>> 3) & 0xf);

        insulinNotDelivered = OmnipodConstants.POD_PULSE_SIZE * (((encodedData[4] & 0x03) << 8) | ByteUtil.convertUnsignedByteToInt(encodedData[5]));
        alerts = new AlertSet((byte) (((encodedData[6] & 0x7f) << 1) | (ByteUtil.convertUnsignedByteToInt(encodedData[7]) >>> 7)));

        double reservoirValue = (((encodedData[8] & 0x3) << 8) + ByteUtil.convertUnsignedByteToInt(encodedData[9])) * OmnipodConstants.POD_PULSE_SIZE;
        if (reservoirValue > OmnipodConstants.MAX_RESERVOIR_READING) {
            reservoirLevel = null;
        } else {
            reservoirLevel = reservoirValue;
        }
    }

    @Override
    public MessageBlockType getType() {
        return MessageBlockType.STATUS_RESPONSE;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public PodProgressStatus getPodProgressStatus() {
        return podProgressStatus;
    }

    public Duration getTimeActive() {
        return timeActive;
    }

    public Double getReservoirLevel() {
        return reservoirLevel;
    }

    public int getTicksDelivered() {
        return ticksDelivered;
    }

    public double getInsulinDelivered() {
        return insulinDelivered;
    }

    public double getInsulinNotDelivered() {
        return insulinNotDelivered;
    }

    public byte getPodMessageCounter() {
        return podMessageCounter;
    }

    public AlertSet getAlerts() {
        return alerts;
    }

    @Override public byte[] getRawData() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            stream.write(getType().getValue());
            stream.write(encodedData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return stream.toByteArray();
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "deliveryStatus=" + deliveryStatus +
                ", podProgressStatus=" + podProgressStatus +
                ", timeActive=" + timeActive +
                ", reservoirLevel=" + reservoirLevel +
                ", insulinDelivered=" + insulinDelivered +
                ", insulinNotDelivered=" + insulinNotDelivered +
                ", podMessageCounter=" + podMessageCounter +
                ", alerts=" + alerts +
                '}';
    }
}
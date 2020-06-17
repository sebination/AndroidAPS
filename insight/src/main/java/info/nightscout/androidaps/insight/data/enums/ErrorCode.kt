package info.nightscout.androidaps.insight.data.enums

enum class ErrorCode(
    override val id: UShort
) : EnumWithId {
    PUMP_STOPPED(3178u),
    BOLUS_AMOUNT_NOT_IN_RANGE(6017u),
    PUMP_ALREADY_IN_THAT_STATE(3324u),
    INVALID_SERVICE_PASSWORD(61593u),
    UNKNOWN_COMMAND(61455u),
    ALREADY_CONNECTED(61455u),
    WRONG_STATE(61680u),
    SERVICE_INCOMPATIBLE(61542u),
    UNKNOWN_SERVICE(61545u),
    NO_SERVICE_PASSWORD_NEEDED(61695u),
    SERVICE_ALREADY_ACTIVATED(61644u),
    INCOMPATIBLE_VERSION(61491u),
    INVALID_PAYLOAD_LENGTH(61500u),
    NOT_CONNECTED(61525u),
    SERVICE_COMMAND_NOT_AVAILABLE(61605u),
    PUMP_BUSY(61635u),
    NOT_REFERENCED(5335u),
    STEP_COUNT_OUT_OF_RANGE(5348u),
    INVALID_PAYLOAD_CRC(2805u),
    INVALID_PARAMETER_TYPE(2810u),
    COMMAND_EXECUTION_FAILED(22796u),
    INVALID_ALERT_INSTANCE_ID(3238u),
    INVALID_TBR_FACTOR(3241u),
    INVALID_TBR_DURATION(3264u),
    INVALID_TBR_TEMPLATE(6363u),
    PAUSE_MODE_NOT_ALLOWED(3315u),
    RUN_MODE_NOT_ALLOWED(3279u),
    NO_ACTIVE_TBR_TO_CANCEL(3840u),
    BOLUS_TYPE_AND_PARAMETERS_MISMATCH(3925u),
    INVALID_DURATION_PRESET(5924u),
    BOLUS_LAG_TIME_FEATURE_DISABLED(90u),
    BOLUS_DURATION_NOT_IN_RANGE(6014u),
    INVALID_VALUES_OF_TWO_CHANNEL_TRANSMISSION(3990u),
    NO_SUCH_BOLUS_TO_CANCEL(4005u),
    MAXIMUM_NUMBER_OF_BOLUS_TYPE_ALREADY_RUNNING(4010u),
    CUSTOM_BOLUS_NOT_CONFIGURED(6270u),
    INVALID_DATE_PARAMETER(4044u),
    INVALID_TIME_PARAMETER(4080u),
    NO_CONFIG_BLOCK_DATA(4471u),
    INVALID_CONFIG_BLOCK_ID(4472u),
    INVALID_CONFIG_BLOCK_CRC(4487u),
    INVALID_CONFIG_BLOCK_LENGTH(6286u),
    WRITE_SESSION_ALREADY_OPEN(4539u),
    WRITE_SESSION_CLOSED(4562u),
    CONFIG_MEMORY_ACCESS(4573u),
    READING_HISTORY_ALREADY_STARTED(11794u),
    READING_HISTORY_NOT_STARTED(4680u),
    INVALID_PAYLOAD(6210u),
    IMPLAUSIBLE_PORTION_LENGTH_VALUE(4824u),
    NOT_ALLOWED_TO_ACCESS_POSITION_ZERO(4830u),
    POSITION_PROTECTED(4845u),
    INVALID_LAG_TIME(3891u),
    NO_ACTIVE_TBR_TO_CHANGE(6322u),
    SERVICE_NOT_ACTIVATED(61610u),
    SERVICE_ACTIVATION_NOT_ALLOWED(61590u)
}
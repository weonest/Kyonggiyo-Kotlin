package kyonggiyo.common.exception

abstract class BusinessException : RuntimeException {

    private val errorCode: ErrorCode
    private val loggingMessage: String

    protected constructor(errorCode: ErrorCode) {
        this.errorCode = errorCode
        loggingMessage = errorCode.message()
    }

    protected constructor(errorCode: ErrorCode, loggingMessage: String) {
        this.errorCode = errorCode
        this.loggingMessage = loggingMessage
    }

}

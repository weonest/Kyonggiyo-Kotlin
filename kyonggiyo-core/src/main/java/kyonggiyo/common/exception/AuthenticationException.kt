package kyonggiyo.common.exception

open class AuthenticationException : BusinessException {
    constructor(errorCode: ErrorCode) : super(errorCode)
    constructor(errorCode: ErrorCode, loggingMessage: String) : super(errorCode, loggingMessage)
}

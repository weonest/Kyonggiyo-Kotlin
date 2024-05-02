package kyonggiyo.application.auth.domain.exception

import kyonggiyo.common.exception.AuthenticationException
import kyonggiyo.common.exception.ErrorCode

class InvalidTokenException : AuthenticationException {
    constructor(errorCode: ErrorCode?) : super(errorCode!!)
    constructor(errorCode: ErrorCode?, loggingMessage: String?) : super(errorCode!!, loggingMessage!!)
}

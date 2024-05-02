package kyonggiyo.application.image.domain.exception

import kyonggiyo.common.exception.BusinessException
import kyonggiyo.common.exception.ErrorCode

class ImageException : BusinessException {
    constructor(errorCode: ErrorCode?) : super(errorCode!!)
    constructor(errorCode: ErrorCode?, loggingMessage: String?) : super(errorCode!!, loggingMessage!!)
}

package kyonggiyo.application.candidate.domain.vo

import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.common.exception.InvalidValueException

enum class Status {
    ACCEPTED,
    WAITING;

    companion object {
        fun from(status: String): Status {
            return values().find { it.name == status.uppercase() }
                ?: throw InvalidValueException(GlobalErrorCode.INVALID_REQUEST_EXCEPTION)
        }
    }

}

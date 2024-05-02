package kyonggiyo.application.auth.domain.vo

import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.common.exception.InvalidValueException
import java.util.*

enum class Platform {
    NAVER,
    KAKAO;

    companion object {
        fun from(platform: String): Platform {
            return values().find { it.name == platform.uppercase() }
                    ?: throw InvalidValueException(GlobalErrorCode.INVALID_REQUEST_EXCEPTION)
        }
    }

}

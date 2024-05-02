package kyonggiyo.application.auth.domain.exception

import kyonggiyo.common.exception.ErrorCode

enum class TokenErrorCode(
        private val code: String,
        private val message: String
) : ErrorCode {
    EXPIRED_TOKEN_EXCEPTION("T001", "만료된 토큰입니다."),
    INVALID_TOKEN_EXCEPTION("T002", "유효하지 않은 토큰입니다.");

    override fun code(): String {
        return code
    }

    override fun message(): String {
        return message
    }
}

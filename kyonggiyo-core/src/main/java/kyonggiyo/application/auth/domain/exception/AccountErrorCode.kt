package kyonggiyo.application.auth.domain.exception

import kyonggiyo.common.exception.ErrorCode

enum class AccountErrorCode(
        val code: String,
        val message: String
) : ErrorCode {

    ALREADY_HAS_USER("A001", "이미 유저 정보가 존재합니다.");

    override fun code() = code

    override fun message() = message

}

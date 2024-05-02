package kyonggiyo.application.port.`in`.user.dto

data class UserCreateCommand(
    val accountId: Long,
    val nickname: String
)

package kyonggiyo.api.adapter.controller.user.dto

data class UserCreateRequest(
    val accountId: Long,
    val nickname: String
) {

    fun toCommand() = UserCreateCommand(accountId, nickname)

}

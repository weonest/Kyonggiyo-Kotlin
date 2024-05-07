package kyonggiyo.api.adapter.controller.user.dto

data class UserDeleteRequest(
    val accountId: Long
) {
    fun toCommand(): UserDeleteCommand = UserDeleteCommand(accountId)
}
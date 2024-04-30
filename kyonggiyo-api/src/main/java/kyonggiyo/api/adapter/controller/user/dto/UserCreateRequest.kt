package kyonggiyo.api.adapter.controller.user.dto

import kyonggiyo.application.port.`in`.user.dto.UserCreateCommand

data class UserCreateRequest(
    val accountId: Long,
    val nickname: String
) {

    fun toCommand() = UserCreateCommand(accountId, nickname)

}

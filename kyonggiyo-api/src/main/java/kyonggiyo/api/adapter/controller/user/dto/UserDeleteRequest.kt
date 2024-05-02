package kyonggiyo.api.adapter.controller.user.dto

import kyonggiyo.application.port.`in`.user.dto.UserDeleteCommand

data class UserDeleteRequest(
    val accountId: Long
) {
    fun toCommand(): UserDeleteCommand = UserDeleteCommand(accountId)
}
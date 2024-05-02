package kyonggiyo.application.user.port.inbound

import kyonggiyo.application.auth.domain.vo.Platform

interface CreateUserUseCase {
    fun createUser(command: UserCreateCommand): Platform
}

data class UserCreateCommand(
    val accountId: Long,
    val nickname: String
)
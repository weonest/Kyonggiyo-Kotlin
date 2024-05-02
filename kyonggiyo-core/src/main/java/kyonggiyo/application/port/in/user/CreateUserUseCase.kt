package kyonggiyo.application.port.`in`.user

import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.port.`in`.user.dto.UserCreateCommand

interface CreateUserUseCase {
    fun createUser(command: UserCreateCommand): Platform
}
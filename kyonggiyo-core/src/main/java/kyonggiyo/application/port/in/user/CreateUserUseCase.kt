package kyonggiyo.application.port.`in`.user

import kyonggiyo.application.port.`in`.user.dto.UserCreateCommand
import kyonggiyo.domain.auth.Platform

interface CreateUserUseCase {
    fun createUser(command: UserCreateCommand): Platform
}
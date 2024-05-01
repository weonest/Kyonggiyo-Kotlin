package kyonggiyo.application.port.`in`.user

import kyonggiyo.application.port.`in`.user.dto.UserDeleteCommand

interface WithdrawUserUseCase {
    fun deleteUser(command: UserDeleteCommand)
}
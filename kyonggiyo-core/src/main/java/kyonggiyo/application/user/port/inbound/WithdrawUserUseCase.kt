package kyonggiyo.application.user.port.inbound

interface WithdrawUserUseCase {
    fun deleteUser(command: UserDeleteCommand)
}

data class UserDeleteCommand(
    val accountId: Long
)
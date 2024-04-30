package kyonggiyo.application.service.user

import kyonggiyo.application.port.`in`.user.CreateUserUseCase
import kyonggiyo.application.port.`in`.user.WithdrawUserUseCase
import kyonggiyo.application.port.`in`.user.dto.UserCreateCommand
import kyonggiyo.application.port.`in`.user.dto.UserDeleteCommand
import kyonggiyo.application.port.out.auth.LoadAccountPort
import kyonggiyo.application.port.out.user.LoadUserPort
import kyonggiyo.application.port.out.user.SaveUserPort
import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.common.exception.NotFoundException
import kyonggiyo.domain.auth.Platform
import kyonggiyo.domain.user.Role
import kyonggiyo.domain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserCommandService(
    private val loadAccountPort: LoadAccountPort,
    private val saveUserPort: SaveUserPort,
    private val loadUserPort: LoadUserPort,
): CreateUserUseCase {

    override fun createUser(command: UserCreateCommand): Platform {
        val account = loadAccountPort.findById(command.accountId)
            ?: throw NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION)

        val user = User(
            role = Role.USER,
            nickname = command.nickname,
        )
        account.registerUser(saveUserPort.save(user))
        return account.platform
    }
}
package kyonggiyo.application.user.service

import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.auth.port.outbound.LoadAccountPort
import kyonggiyo.application.user.port.inbound.CreateUserUseCase
import kyonggiyo.application.user.port.inbound.WithdrawUserUseCase
import kyonggiyo.application.user.port.outbound.LoadUserPort
import kyonggiyo.application.user.port.outbound.SaveUserPort
import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.common.exception.NotFoundException
import kyonggiyo.application.user.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserCommandService(
    private val loadAccountPort: LoadAccountPort,
    private val saveUserPort: SaveUserPort,
    private val loadUserPort: LoadUserPort,
) : CreateUserUseCase, WithdrawUserUseCase {

    override fun createUser(command: UserCreateCommand): Platform {
        val account = loadAccountPort.findById(command.accountId)
                ?: throw NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION)

        saveUserPort.save(
                User(nickname = command.nickname)
        ).also { account.registerUser(it.id!!) }
        return account.platform
    }

    override fun deleteUser(command: UserDeleteCommand) {
        val account = loadAccountPort.findById(command.accountId)
                ?: throw NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION)

        loadUserPort.getById(account.userId!!)
                .let { it.delete() }
    }

}

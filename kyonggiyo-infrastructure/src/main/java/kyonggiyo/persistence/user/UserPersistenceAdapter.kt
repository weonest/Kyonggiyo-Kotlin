package kyonggiyo.persistence.user

import kyonggiyo.application.user.port.outbound.ExistUserPort
import kyonggiyo.application.user.port.outbound.LoadUserPort
import kyonggiyo.application.user.port.outbound.SaveUserPort
import kyonggiyo.application.user.domain.entity.User
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val repository: UserRepository,
) : LoadUserPort, ExistUserPort, SaveUserPort {

    override fun existByNickname(nickname: String): Boolean = repository.existByNickname(nickname)

    override fun getById(id: Long): User = repository.getById(id)

    override fun save(user: User): User = repository.save(user)

}
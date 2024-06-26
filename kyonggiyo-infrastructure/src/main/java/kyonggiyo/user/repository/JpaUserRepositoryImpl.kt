package kyonggiyo.user.repository

import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.common.exception.NotFoundException
import kyonggiyo.application.user.domain.entity.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepositoryImpl(
    private val jpaRepository: UserJpaRepository,
) : UserRepository {

    override fun getById(id: Long): User {
        return jpaRepository.findByIdOrNull(id)
            ?: throw NotFoundException(GlobalErrorCode.NOT_FOUND_ENTITY_EXCEPTION)
    }

    override fun existByNickname(nickname: String): Boolean = jpaRepository.existsByNickname(nickname)

    override fun save(user: User): User {
        return jpaRepository.save(user)
    }
}
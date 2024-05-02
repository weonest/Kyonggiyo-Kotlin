package kyonggiyo.persistence.user

import kyonggiyo.application.user.domain.entity.User

interface UserRepository {
    fun getById(id: Long): User

    fun existByNickname(nickname: String): Boolean

    fun save(user: User): User
}
package kyonggiyo.user.repository

import kyonggiyo.application.user.domain.entity.User

interface UserRepository {
    fun getById(id: Long): User

    fun existByNickname(nickname: String): Boolean

    fun save(user: User): User
}
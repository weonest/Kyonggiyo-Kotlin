package kyonggiyo.user.repository

import kyonggiyo.application.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long> {
    fun existsByNickname(nickname: String): Boolean
}
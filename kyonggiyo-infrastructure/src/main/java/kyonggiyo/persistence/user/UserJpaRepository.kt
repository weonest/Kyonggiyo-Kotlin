package kyonggiyo.persistence.user

import kyonggiyo.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long> {
    fun existsByNickname(nickname: String): Boolean
}
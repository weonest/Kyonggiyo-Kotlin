package kyonggiyo.application.port.out.user

import kyonggiyo.domain.user.User

interface SaveUserPort {
    fun save(user: User): User
}
package kyonggiyo.application.user.port.outbound

import kyonggiyo.application.user.domain.entity.User

interface SaveUserPort {
    fun save(user: User): User
}
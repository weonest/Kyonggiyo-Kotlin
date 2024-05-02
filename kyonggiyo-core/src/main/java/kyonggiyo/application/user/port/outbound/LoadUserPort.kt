package kyonggiyo.application.user.port.outbound

import kyonggiyo.application.user.domain.entity.User

interface LoadUserPort {
    fun getById(id: Long): User
}
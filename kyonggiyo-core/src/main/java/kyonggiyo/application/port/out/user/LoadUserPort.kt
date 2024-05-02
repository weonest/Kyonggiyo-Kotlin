package kyonggiyo.application.port.out.user

import kyonggiyo.domain.user.User

interface LoadUserPort {
    fun getById(id: Long): User
}
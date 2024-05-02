package kyonggiyo.application.auth.domain.vo

import kyonggiyo.domain.user.Role

data class AuthInfo(
        val userId: Long,
        val role: Role
)

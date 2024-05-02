package kyonggiyo.application.auth.domain.vo

import kyonggiyo.application.user.domain.vo.Role

data class AuthInfo(
        val userId: Long,
        val role: Role
)

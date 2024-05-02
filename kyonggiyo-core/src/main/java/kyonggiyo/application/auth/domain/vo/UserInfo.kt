package kyonggiyo.application.auth.domain.vo

import kyonggiyo.application.user.domain.vo.Role

data class UserInfo(
        val userId: Long,
        val role: Role
) {

    companion object {
        fun from(authInfo: AuthInfo): UserInfo {
            return UserInfo(authInfo.userId, authInfo.role)
        }
    }

}

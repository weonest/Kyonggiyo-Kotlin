package kyonggiyo.application.auth.service

import kyonggiyo.application.auth.domain.entity.RefreshToken
import kyonggiyo.application.auth.domain.vo.AccessToken
import kyonggiyo.application.auth.domain.vo.AuthInfo
import kyonggiyo.application.user.domain.vo.Role

interface TokenManager {

    fun generateAccessToken(userId: Long, role: Role): AccessToken

    fun generateRefreshToken(userId: Long): RefreshToken

    fun validate(token: String)

    fun extract(token: String): AuthInfo

}

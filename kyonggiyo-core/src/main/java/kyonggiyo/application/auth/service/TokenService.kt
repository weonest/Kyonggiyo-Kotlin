package kyonggiyo.application.auth.service

import kyonggiyo.application.auth.domain.vo.AuthInfo
import kyonggiyo.application.auth.port.inbound.TokenResponse
import kyonggiyo.application.auth.port.outbound.DeleteRefreshTokenPort
import kyonggiyo.application.auth.port.outbound.LoadRefreshTokenPort
import kyonggiyo.application.auth.port.outbound.SaveRefreshTokenPort
import kyonggiyo.common.exception.AuthenticationException
import kyonggiyo.common.exception.GlobalErrorCode
import kyonggiyo.domain.user.Role
import kyonggiyo.domain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class TokenService(
        private val tokenManager: TokenManager,
        private val saveRefreshTokenPort: SaveRefreshTokenPort,
        private val deleteRefreshTokenPort: DeleteRefreshTokenPort,
        private val loadRefreshTokenPort: LoadRefreshTokenPort
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun generateToken(user: User): TokenResponse {
        val accessToken = tokenManager.generateAccessToken(user.id!!, user.role)
        val refreshToken = tokenManager.generateRefreshToken(user.id!!)
        saveRefreshTokenPort.save(refreshToken)

        return TokenResponse(accessToken = accessToken.value,
                accessTokenMaxAge = accessToken.expiresIn,
                refreshToken = refreshToken.value,
                refreshTokenMaxAge = refreshToken.expiresIn
        )
    }

    @Transactional
    fun deleteRefreshTokenByUserId(userId: Long) {
        deleteRefreshTokenPort.deleteByUserId(userId)
    }

    fun validate(token: String) {
        tokenManager.validate(token)
    }

    fun getAuthInfo(token: String): AuthInfo {
        return tokenManager.extract(token)
    }

    @Transactional
    fun reissueToken(id: Long, role: Role, refreshToken: String): TokenResponse {
        loadRefreshTokenPort.getByUserId(id)
                .let {
                    if (!it.equals(refreshToken)) {
                        throw AuthenticationException(GlobalErrorCode.NO_AUTHENTICATION_INFO_EXCEPTION)
                    }
                }

        val newAccessToken = tokenManager.generateAccessToken(id, role)
        val newRefreshToken = tokenManager.generateRefreshToken(id)
        saveRefreshTokenPort.save(newRefreshToken)

        return TokenResponse(accessToken = newAccessToken.value,
                accessTokenMaxAge = newAccessToken.expiresIn,
                refreshToken = newRefreshToken.value,
                refreshTokenMaxAge = newRefreshToken.expiresIn)
    }
}

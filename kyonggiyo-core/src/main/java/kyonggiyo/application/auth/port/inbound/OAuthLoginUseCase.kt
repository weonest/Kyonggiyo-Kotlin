package kyonggiyo.application.auth.port.inbound

import com.fasterxml.jackson.annotation.JsonIgnore
import kyonggiyo.application.auth.domain.vo.Platform

interface OAuthLoginUseCase {

    fun login(platform: Platform, code: String): LogInResponse?

}


data class LogInResponse(
        val accountId: Long,
        val token: TokenResponse? = null
) {
    companion object {
        fun from(id: Long): LogInResponse {
            return LogInResponse(id)
        }

        fun of(id: Long, token: TokenResponse): LogInResponse {
            return LogInResponse(id, token)
        }
    }
}

data class TokenResponse(
        val accessToken: String,
        val accessTokenMaxAge: Long,
        @JsonIgnore
        val refreshToken: String,
        val refreshTokenMaxAge: Long
)

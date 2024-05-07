package kyonggiyo.auth.entity

import kyonggiyo.application.auth.domain.entity.RefreshToken
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "refreshToken", timeToLive = 259200)
class RefreshTokenEntity(
    @Id
    val userId: Long,
    val value: String,
    val expiresIn: Long,
) {

    fun toDomain(): RefreshToken {
        return RefreshToken(
            userId = userId,
            value = value,
            expiresIn = expiresIn
        )
    }

    companion object {
        fun from(refreshToken: RefreshToken): RefreshTokenEntity {
            return RefreshTokenEntity(
                userId = refreshToken.userId,
                value = refreshToken.value,
                expiresIn = refreshToken.expiresIn
            )
        }
    }

}

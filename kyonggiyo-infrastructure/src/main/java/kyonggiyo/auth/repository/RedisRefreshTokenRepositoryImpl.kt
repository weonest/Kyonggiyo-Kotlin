package kyonggiyo.auth.repository

import kyonggiyo.application.auth.domain.entity.RefreshToken
import kyonggiyo.application.auth.domain.exception.ExpiredTokenException
import kyonggiyo.auth.entity.RefreshTokenEntity
import kyonggiyo.common.exception.GlobalErrorCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class RedisRefreshTokenRepositoryImpl(
    private val redisRepository: RefreshTokenDataRedisRepository
) : RefreshTokenRepository {

    override fun save(refreshToken: RefreshToken) {
        redisRepository.save(RefreshTokenEntity.from(refreshToken))
    }

    override fun getByUserId(userId: Long): RefreshToken {
        return redisRepository.findByIdOrNull(userId)?.let { it.toDomain() }
            ?: throw ExpiredTokenException(GlobalErrorCode.NO_AUTHENTICATION_INFO_EXCEPTION)
    }

    override fun deleteByUserId(userId: Long) {
        redisRepository.deleteById(userId)
    }

}

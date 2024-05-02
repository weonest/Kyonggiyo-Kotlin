package kyonggiyo.auth.adapter

import kyonggiyo.application.auth.domain.entity.RefreshToken
import kyonggiyo.application.auth.port.outbound.DeleteRefreshTokenPort
import kyonggiyo.application.auth.port.outbound.LoadRefreshTokenPort
import kyonggiyo.application.auth.port.outbound.SaveRefreshTokenPort
import kyonggiyo.auth.repository.RefreshTokenRepository
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenAdapter(
    private val refreshTokenRepository: RefreshTokenRepository
) : SaveRefreshTokenPort, LoadRefreshTokenPort, DeleteRefreshTokenPort {

    override fun save(refreshToken: RefreshToken) {
        refreshTokenRepository.save(refreshToken)
    }

    override fun getByUserId(userId: Long): RefreshToken {
        return refreshTokenRepository.getByUserId(userId)
    }

    override fun deleteByUserId(userId: Long) {
        refreshTokenRepository.deleteByUserId(userId)
    }
}

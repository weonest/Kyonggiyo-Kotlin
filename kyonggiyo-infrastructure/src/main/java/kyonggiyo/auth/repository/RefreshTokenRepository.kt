package kyonggiyo.auth.repository

import kyonggiyo.application.auth.domain.entity.RefreshToken

interface RefreshTokenRepository {

    fun save(refreshToken: RefreshToken)

    fun getByUserId(userId: Long): RefreshToken

    fun deleteByUserId(userId: Long)

}

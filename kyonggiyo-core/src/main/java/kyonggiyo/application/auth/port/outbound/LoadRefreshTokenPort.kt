package kyonggiyo.application.auth.port.outbound

import kyonggiyo.application.auth.domain.entity.RefreshToken

interface LoadRefreshTokenPort {

    fun getByUserId(userId: Long): RefreshToken

}

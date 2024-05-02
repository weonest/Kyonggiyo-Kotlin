package kyonggiyo.application.auth.port.outbound

import kyonggiyo.application.auth.domain.entity.RefreshToken

interface SaveRefreshTokenPort {

    fun save(refreshToken: RefreshToken)

}

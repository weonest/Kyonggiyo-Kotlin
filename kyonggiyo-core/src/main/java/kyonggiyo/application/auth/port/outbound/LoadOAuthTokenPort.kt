package kyonggiyo.application.auth.port.outbound

import kyonggiyo.application.auth.domain.vo.Platform

interface LoadOAuthTokenPort {

    fun requestToken(platform: Platform, authCode: String): String

}

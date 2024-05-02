package kyonggiyo.application.auth.port.outbound

import kyonggiyo.application.auth.domain.vo.Platform

interface LoadOAuthUserInfoPort {

    fun requestUserInfo(platform: Platform, accessToken: String): String

}

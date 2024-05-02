package kyonggiyo.application.auth.service

import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.auth.port.outbound.LoadOAuthTokenPort
import kyonggiyo.application.auth.port.outbound.LoadOAuthUserInfoPort
import org.springframework.stereotype.Service

@Service
class OAuthQueryService(
        private val loadOAuthTokenPort: LoadOAuthTokenPort,
        private val loadOAuthUserInfoPort: LoadOAuthUserInfoPort
) {

    fun getProviderId(platform: Platform, authCode: String): String {
        return loadOAuthTokenPort.requestToken(platform, authCode)
                .let { loadOAuthUserInfoPort.requestUserInfo(platform, it) }
    }

}

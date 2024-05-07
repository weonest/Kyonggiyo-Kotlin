package kyonggiyo.application.auth.service

import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.auth.port.inbound.LogInResponse
import kyonggiyo.application.auth.port.inbound.LogInResponse.Companion.from
import kyonggiyo.application.auth.port.inbound.LogInResponse.Companion.of
import kyonggiyo.application.auth.port.inbound.OAuthLoginUseCase
import kyonggiyo.application.auth.port.inbound.OAuthLogoutUseCase
import kyonggiyo.application.user.port.outbound.LoadUserPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthFacadeService(
        private val tokenService: TokenService,
        private val oAuthQueryService: OAuthQueryService,
        private val accountLoginService: AccountLoginService,
        private val loadUserPort: LoadUserPort
) : OAuthLoginUseCase, OAuthLogoutUseCase {

    @Transactional(readOnly = true)
    override fun login(platform: Platform, code: String): LogInResponse {
        val platformId = oAuthQueryService.getProviderId(platform, code)
        val account = accountLoginService.login(platform, platformId)

        if (account.hasNoUser()) {
            return from(account.id!!)
        }

        val tokenResponse = loadUserPort.getById(account.userId!!)
                .let { tokenService.generateToken(it) }

        return of(account.userId!!, tokenResponse)
    }

    @Transactional
    override fun logout(userId: Long) {
        tokenService.deleteRefreshTokenByUserId(userId)
    }
}

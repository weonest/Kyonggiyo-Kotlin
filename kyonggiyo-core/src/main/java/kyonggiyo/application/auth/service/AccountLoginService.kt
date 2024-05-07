package kyonggiyo.application.auth.service

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.auth.port.outbound.LoadAccountPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountLoginService(
        private val loadAccountPort: LoadAccountPort,
        private val accountSignUpService: AccountSignUpService
) {

    @Transactional(readOnly = true)
    fun login(platform: Platform, platformId: String): Account {
        return loadAccountPort.findByPlatformAndPlatformId(platform, platformId)
                ?: accountSignUpService.signup(platform, platformId)
    }

}

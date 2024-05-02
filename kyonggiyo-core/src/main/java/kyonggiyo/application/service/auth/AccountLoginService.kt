package kyonggiyo.application.service.auth

import kyonggiyo.application.port.out.auth.LoadAccountPort
import kyonggiyo.domain.auth.Account
import kyonggiyo.domain.auth.Platform
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

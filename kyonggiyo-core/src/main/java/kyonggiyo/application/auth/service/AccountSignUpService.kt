package kyonggiyo.application.auth.service

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.auth.port.outbound.SaveAccountPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class AccountSignUpService(
    private val saveAccountPort: SaveAccountPort
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun signup(platform: Platform, platformId: String): Account {
        val account = Account(platform, platformId)
        return saveAccountPort.save(account)
    }
}

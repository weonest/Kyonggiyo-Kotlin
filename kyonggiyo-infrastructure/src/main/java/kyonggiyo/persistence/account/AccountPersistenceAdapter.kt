package kyonggiyo.persistence.account

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform
import kyonggiyo.application.auth.port.outbound.LoadAccountPort
import kyonggiyo.application.auth.port.outbound.SaveAccountPort
import org.springframework.stereotype.Component

@Component
class AccountPersistenceAdapter(
        private val repository: AccountRepository
) : LoadAccountPort, SaveAccountPort {

    override fun findById(accountId: Long): Account? {
        return repository.findById(accountId)
    }

    override fun findByPlatformAndPlatformId(platform: Platform, platformId: String): Account? {
        return repository.findByPlatformAndPlatformId(platform, platformId)
    }

    override fun save(account: Account): Account {
        return repository.save(account)
    }

}

package kyonggiyo.persistence.account

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class JpaAccountRepositoryImpl(
        private val jpaRepository: AccountJpaRepository
) : AccountRepository {

    override fun findById(accountId: Long): Account? {
        return jpaRepository.findByIdOrNull(accountId)
    }

    override fun findByPlatformAndPlatformId(platform: Platform, platformId: String): Account? {
        return jpaRepository.findByPlatformAndPlatformId(platform, platformId)
    }

    override fun save(account: Account): Account {
        return jpaRepository.save(account)
    }

}

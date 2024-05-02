package kyonggiyo.auth.repository

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform
import org.springframework.data.jpa.repository.JpaRepository

interface AccountJpaRepository : JpaRepository<Account, Long> {

    fun findByPlatformAndPlatformId(platform: Platform, platformId: String): Account?

}

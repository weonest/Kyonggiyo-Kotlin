package kyonggiyo.auth.repository

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform

interface AccountRepository {

    fun findById(accountId: Long): Account?

    fun findByPlatformAndPlatformId(platform: Platform, platformId: String): Account?

    fun save(account: Account): Account

}

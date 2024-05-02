package kyonggiyo.application.auth.port.outbound

import kyonggiyo.application.auth.domain.entity.Account
import kyonggiyo.application.auth.domain.vo.Platform

interface LoadAccountPort {

    fun findById(accountId: Long): Account?

    fun findByPlatformAndPlatformId(platform: Platform, platformId:  String): Account?

}
package kyonggiyo.application.port.out.auth

import kyonggiyo.domain.auth.Account
import kyonggiyo.domain.auth.Platform

interface LoadAccountPort {
    fun findById(accountId: Long): Account?
    fun findByPlatformAndPlatformId(platform: Platform, platformId:  String): Account?
}
package kyonggiyo.application.auth.port.outbound

import kyonggiyo.application.auth.domain.entity.Account

interface SaveAccountPort {

    fun save(account: Account): Account

}

package kyonggiyo.fixture

import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import com.navercorp.fixturemonkey.kotlin.setNullExp
import kyonggiyo.IdGenerator
import kyonggiyo.ReflectionMonkey
import kyonggiyo.domain.auth.Account
import kyonggiyo.domain.auth.Platform
import net.jqwik.api.Arbitraries

object AccountFixtures {

    fun generateEntity(): Account {
        return ReflectionMonkey.giveMeBuilder<Account>()
                .setExp(Account::id, IdGenerator.getId())
                .setExp(Account::platform, Arbitraries.of(Platform.KAKAO, Platform.NAVER))
                .setExp(Account::platformId, Arbitraries.strings().withCharRange('a', 'z').ofMaxLength(10))
                .setExp(Account::userId, IdGenerator.getId())
                .sample()
    }

    fun generateWithoutUserEntity(): Account {
        return ReflectionMonkey.giveMeBuilder<Account>()
                .setExp(Account::id, IdGenerator.getId())
                .setExp(Account::platform, Arbitraries.of(Platform.KAKAO, Platform.NAVER))
                .setExp(Account::platformId, Arbitraries.strings().withCharRange('a', 'z').ofMaxLength(10))
                .setNullExp(Account::userId)
                .sample()
    }

}

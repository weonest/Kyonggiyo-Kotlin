package kyonggiyo.fixture

import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.setExp
import kyonggiyo.IdGenerator
import kyonggiyo.ReflectionMonkey
import kyonggiyo.application.user.domain.entity.User
import net.jqwik.api.Arbitraries


object UserFixtures {

    fun generateEntity(): User {
        return ReflectionMonkey.giveMeBuilder<User>()
                .setExp(User::id, IdGenerator.getId())
                .setExp(User::nickname, Arbitraries.strings().withCharRange('0', 'z'))
                .setExp(User::isDeleted, false)
                .sample()
    }

    fun generateEntity(id: Long): User {
        return ReflectionMonkey.giveMeBuilder<User>()
                .setExp(User::id, id)
                .setExp(User::nickname, Arbitraries.strings().withCharRange('0', 'z'))
                .setExp(User::isDeleted, false)
                .sample()
    }

}

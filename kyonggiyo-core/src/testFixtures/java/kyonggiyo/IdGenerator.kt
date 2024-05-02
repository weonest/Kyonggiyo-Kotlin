package kyonggiyo

import com.navercorp.fixturemonkey.kotlin.giveMeBuilder

object IdGenerator {

    private var variable = 0L
    private val idBuilder = ReflectionMonkey.giveMeBuilder<Long>()
            .setLazy("$") { variable++ }

    fun getId(): Long {
        return idBuilder.sample()
    }

}


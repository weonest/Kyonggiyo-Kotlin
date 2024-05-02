package kyonggiyo

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector

val ReflectionMonkey = FixtureMonkey.builder()
        .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
        .build()



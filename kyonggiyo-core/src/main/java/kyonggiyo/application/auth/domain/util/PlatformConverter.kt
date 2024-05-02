package kyonggiyo.application.auth.domain.util

import kyonggiyo.application.auth.domain.vo.Platform
import org.springframework.core.convert.converter.Converter

class PlatformConverter : Converter<String, Platform> {
    override fun convert(platform: String): Platform {
        return Platform.from(platform)
    }
}

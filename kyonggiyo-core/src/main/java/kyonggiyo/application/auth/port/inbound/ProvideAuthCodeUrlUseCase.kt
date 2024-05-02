package kyonggiyo.application.auth.port.inbound

import kyonggiyo.application.auth.domain.vo.Platform
import java.net.URI

interface ProvideAuthCodeUrlUseCase {

    fun provideUri(platform: Platform): URI

}

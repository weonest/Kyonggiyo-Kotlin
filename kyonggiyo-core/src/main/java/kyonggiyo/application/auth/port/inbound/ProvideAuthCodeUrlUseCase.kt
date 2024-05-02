package kyonggiyo.application.auth.port.inbound;

import kyonggiyo.application.auth.domain.vo.Platform;

import java.net.URI;

public interface ProvideAuthCodeUrlUseCase {

    URI provideUri(Platform platform);

}

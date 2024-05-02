package kyonggiyo.application.auth.port.outbound;

import kyonggiyo.application.auth.domain.vo.Platform;

public interface LoadOAuthTokenPort {

    String requestToken(Platform platform, String authCode);

}

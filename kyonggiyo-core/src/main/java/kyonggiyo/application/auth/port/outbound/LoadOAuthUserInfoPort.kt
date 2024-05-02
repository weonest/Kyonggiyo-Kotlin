package kyonggiyo.application.auth.port.outbound;

import kyonggiyo.application.auth.domain.vo.Platform;

public interface LoadOAuthUserInfoPort {

    String requestUserInfo(Platform platform, String accessToken);

}

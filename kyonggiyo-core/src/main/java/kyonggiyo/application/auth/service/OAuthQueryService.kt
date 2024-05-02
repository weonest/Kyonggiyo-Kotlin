package kyonggiyo.application.auth.service;

import kyonggiyo.application.auth.domain.vo.Platform;
import kyonggiyo.application.auth.port.outbound.LoadOAuthTokenPort;
import kyonggiyo.application.auth.port.outbound.LoadOAuthUserInfoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthQueryService {

    private final LoadOAuthTokenPort loadOAuthTokenPort;
    private final LoadOAuthUserInfoPort loadOAuthUserInfoPort;

    public String getProviderId(Platform platform, String authCode) {
        String accessToken = loadOAuthTokenPort.requestToken(platform, authCode);
        return loadOAuthUserInfoPort.requestUserInfo(platform, accessToken);
    }
    
}

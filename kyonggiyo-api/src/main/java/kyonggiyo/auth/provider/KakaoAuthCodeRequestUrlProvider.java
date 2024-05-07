package kyonggiyo.auth.provider;

import kyonggiyo.common.property.KakaoOAuthProperties;
import kyonggiyo.application.auth.domain.vo.Platform;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class KakaoAuthCodeRequestUrlProvider extends AuthCodeRequestUrlProvider{

    private static final String AUTHORIZATION_URL = "https://kauth.kakao.com/oauth/authorize";

    private final KakaoOAuthProperties kakaoOAuthProperties;

    public KakaoAuthCodeRequestUrlProvider(KakaoOAuthProperties kakaoOAuthProperties) {
        super(Platform.KAKAO);
        this.kakaoOAuthProperties = kakaoOAuthProperties;
    }

    @Override
    public URI provideUri() {
        return UriComponentsBuilder
                .fromUriString(AUTHORIZATION_URL)
                .queryParam("client_id", kakaoOAuthProperties.getClientId())
                .queryParam("redirect_uri", kakaoOAuthProperties.getUrl().getRedirectUrl())
                .queryParam("response_type", kakaoOAuthProperties.getResponseType())
                .build().toUri();
    }

}

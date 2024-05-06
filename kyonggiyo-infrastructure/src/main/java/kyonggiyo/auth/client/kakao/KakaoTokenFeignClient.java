package kyonggiyo.auth.client.kakao;

import kyonggiyo.client.oauth.response.OAuthTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "kakao-token", url = "${oauth.kakao.url.auth-url}")
public interface KakaoTokenFeignClient {
    // @SpringQueryMap
    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    OAuthTokenResponse getAccessToken(Map<String, ?> params);

}

package kyonggiyo.auth.client.naver;

import kyonggiyo.client.oauth.response.OAuthUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "naver-resource", url = "https://openapi.naver.com")
public interface NaverResourceFeignClient {

    @PostMapping(value = "/v1/nid/me", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    OAuthUserInfoResponse getUserInfo(@RequestHeader("Authorization") String bearerToken);

}

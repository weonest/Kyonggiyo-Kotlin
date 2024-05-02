package kyonggiyo.application.service.auth;


import kyonggiyo.application.auth.domain.entity.Account;
import kyonggiyo.application.auth.domain.vo.Platform;
import kyonggiyo.application.auth.service.AccountLoginService;
import kyonggiyo.application.auth.service.OAuthFacadeService;
import kyonggiyo.application.auth.service.OAuthQueryService;
import kyonggiyo.application.auth.service.TokenService;
import kyonggiyo.application.port.in.auth.dto.LogInResponse;
import kyonggiyo.application.port.in.auth.dto.TokenResponse;
import kyonggiyo.application.service.ServiceTest;
import kyonggiyo.application.user.domain.entity.User;
import kyonggiyo.fixture.UserFixtures;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = OAuthFacadeService.class)
class OAuthFacadeServiceTest extends ServiceTest {

    @Autowired
    private OAuthFacadeService oAuthFacadeService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private OAuthQueryService oAuthQueryService;

    @MockBean
    private AccountLoginService accountLoginService;

    @Test
    void 유저가_없는_계정은_토큰정보가_null인_응답을_반환한다() {
        // given
        Platform platform = Instancio.create(Platform.class);
        String authCode = "authCode";
        String platformId = "platformId";
        Account account = new Account(platform, platformId);

        given(oAuthQueryService.getProviderId(platform, authCode)).willReturn(platformId);
        given(accountLoginService.login(platform, platformId)).willReturn(account);

        // when
        LogInResponse logInResponse = oAuthFacadeService.login(platform, authCode);

        // then
        assertThat(logInResponse.token()).isNull();
    }

    @Test
    void 유저가_있는_계정은_토큰을_발급하여_응답을_반환한다() {
        // given
        Platform platform = Instancio.create(Platform.class);
        String authCode = "authCode";
        String platformId = "platformId";
        Account account = new Account(platform, platformId);
        User user = UserFixtures.INSTANCE.generateEntity();
        account.registerUser(user.getId());
        TokenResponse tokenResponse = Instancio.create(TokenResponse.class);

        given(oAuthQueryService.getProviderId(platform, authCode)).willReturn(platformId);
        given(accountLoginService.login(platform, platformId)).willReturn(account);
        given(tokenService.generateToken(user)).willReturn(tokenResponse);

        // when
        LogInResponse logInResponse = oAuthFacadeService.login(platform, authCode);

        // then
        assertThat(logInResponse.token()).isNotNull();
    }

}

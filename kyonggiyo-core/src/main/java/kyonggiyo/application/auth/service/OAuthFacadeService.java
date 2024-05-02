package kyonggiyo.application.auth.service;

import kyonggiyo.application.auth.domain.entity.Account;
import kyonggiyo.application.auth.domain.vo.Platform;
import kyonggiyo.application.auth.port.inbound.LogInResponse;
import kyonggiyo.application.auth.port.inbound.OAuthLoginUseCase;
import kyonggiyo.application.auth.port.inbound.OAuthLogoutUseCase;
import kyonggiyo.application.auth.port.inbound.TokenResponse;
import kyonggiyo.application.port.out.user.LoadUserPort;
import kyonggiyo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OAuthFacadeService implements OAuthLoginUseCase, OAuthLogoutUseCase {

    private final TokenService tokenService;
    private final OAuthQueryService oAuthQueryService;
    private final AccountLoginService accountLoginService;
    private final LoadUserPort loadUserPort;

    @Override
    @Transactional(readOnly = true)
    public LogInResponse login(Platform platform, String code) {
        String platformId = oAuthQueryService.getProviderId(platform, code);
        Account account = accountLoginService.login(platform, platformId);

        if (account.hasNoUser()) {
            return LogInResponse.Companion.from(account.getId());
        }

        User user = loadUserPort.getById(account.getUserId());
        TokenResponse tokenResponse = tokenService.generateToken(user);

        return LogInResponse.Companion.of(account.getUserId(), tokenResponse);
    }

    @Override
    @Transactional
    public void logout(Long userId) {
        tokenService.deleteRefreshTokenByUserId(userId);
    }

}

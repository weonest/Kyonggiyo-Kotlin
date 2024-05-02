package kyonggiyo.application.auth.service;

import kyonggiyo.application.auth.domain.entity.RefreshToken;
import kyonggiyo.application.auth.domain.vo.AccessToken;
import kyonggiyo.application.auth.domain.vo.AuthInfo;
import kyonggiyo.application.auth.port.inbound.TokenResponse;
import kyonggiyo.application.auth.port.outbound.DeleteRefreshTokenPort;
import kyonggiyo.application.auth.port.outbound.LoadRefreshTokenPort;
import kyonggiyo.application.auth.port.outbound.SaveRefreshTokenPort;
import kyonggiyo.common.exception.AuthenticationException;
import kyonggiyo.common.exception.GlobalErrorCode;
import kyonggiyo.domain.user.Role;
import kyonggiyo.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenManager tokenManager;
    private final SaveRefreshTokenPort saveRefreshTokenPort;
    private final DeleteRefreshTokenPort deleteRefreshTokenPort;
    private final LoadRefreshTokenPort loadRefreshTokenPort;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TokenResponse generateToken(User user) {
        AccessToken accessToken = tokenManager.generateAccessToken(user.getId(), user.getRole());
        RefreshToken refreshToken = tokenManager.generateRefreshToken(user.getId());

        saveRefreshTokenPort.save(refreshToken);

        // TODO: 2024-05-02 해당 클래스를 코틀린 파일로 변경
        return TokenResponse.builder()
                .accessToken(accessToken.value())
                .accessTokenMaxAge(accessToken.expiresIn())
                .refreshToken(refreshToken.getValue())
                .refreshTokenMaxAge(refreshToken.getExpiresIn())
                .build();
    }

    @Transactional
    public void deleteRefreshTokenByUserId(Long userId) {
        deleteRefreshTokenPort.deleteByUserId(userId);
    }

    public void validate(String token) {
        tokenManager.validate(token);
    }

    public AuthInfo getAuthInfo(String token) {
        return tokenManager.extract(token);
    }

    @Transactional
    public TokenResponse reissueToken(Long id, Role role, String refreshToken) {
        RefreshToken retrivedRefreshToken = loadRefreshTokenPort.getByUserId(id);

        if (!Objects.equals(refreshToken, retrivedRefreshToken.getValue())){
            throw new AuthenticationException(GlobalErrorCode.NO_AUTHENTICATION_INFO_EXCEPTION);
        }

        AccessToken newAccessToken = tokenManager.generateAccessToken(id, role);
        RefreshToken newRefreshToken = tokenManager.generateRefreshToken(id);

        saveRefreshTokenPort.save(newRefreshToken);

        // TODO: 2024-05-02 해당 클래스를 코틀린 파일로 변경
        return TokenResponse.builder()
                .accessToken(newAccessToken.value())
                .accessTokenMaxAge(newAccessToken.expiresIn())
                .refreshToken(newRefreshToken.getValue())
                .refreshTokenMaxAge(newRefreshToken.getExpiresIn())
                .build();
    }

}

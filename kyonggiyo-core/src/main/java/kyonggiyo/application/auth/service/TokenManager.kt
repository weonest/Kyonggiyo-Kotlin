package kyonggiyo.application.auth.service;

import kyonggiyo.application.auth.domain.entity.RefreshToken;
import kyonggiyo.application.auth.domain.vo.AccessToken;
import kyonggiyo.application.auth.domain.vo.AuthInfo;
import kyonggiyo.domain.user.Role;

public interface TokenManager {

    AccessToken generateAccessToken(Long userId, Role role);

    RefreshToken generateRefreshToken(Long userId);

    void validate(String token);

    AuthInfo extract(String token);

}

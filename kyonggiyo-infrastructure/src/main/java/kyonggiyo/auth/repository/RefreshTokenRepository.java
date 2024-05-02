package kyonggiyo.auth.repository;

import kyonggiyo.application.auth.domain.entity.RefreshToken;

public interface RefreshTokenRepository {

    void save(RefreshToken refreshToken);

    RefreshToken getByUserId(Long userId);

    void deleteByUserId(Long userId);

}

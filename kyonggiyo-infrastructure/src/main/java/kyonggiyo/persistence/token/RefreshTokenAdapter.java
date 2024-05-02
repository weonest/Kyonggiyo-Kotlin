package kyonggiyo.persistence.token;

import kyonggiyo.application.auth.port.outbound.DeleteRefreshTokenPort;
import kyonggiyo.application.auth.port.outbound.LoadRefreshTokenPort;
import kyonggiyo.application.auth.port.outbound.SaveRefreshTokenPort;
import kyonggiyo.application.auth.domain.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenAdapter implements SaveRefreshTokenPort, LoadRefreshTokenPort,
        DeleteRefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken getByUserId(Long userId) {
        return refreshTokenRepository.getByUserId(userId);
    }

    @Override
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

}

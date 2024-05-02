package kyonggiyo.auth.repository;

import kyonggiyo.application.auth.domain.entity.RefreshToken;
import kyonggiyo.application.auth.domain.exception.ExpiredTokenException;
import kyonggiyo.common.exception.GlobalErrorCode;
import kyonggiyo.auth.entity.RefreshTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenDataRedisRepository redisRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        redisRepository.save(RefreshTokenEntity.from(refreshToken));
    }

    @Override
    public RefreshToken getByUserId(Long userId) {
        RefreshTokenEntity refreshTokenEntity = redisRepository.findById(userId)
                .orElseThrow(() -> new ExpiredTokenException(GlobalErrorCode.NO_AUTHENTICATION_INFO_EXCEPTION));
        return refreshTokenEntity.toDomain();
    }

    @Override
    public void deleteByUserId(Long userId) {
        redisRepository.deleteById(userId);
    }

}

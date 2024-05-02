package kyonggiyo.auth.entity;

import kyonggiyo.application.auth.domain.entity.RefreshToken;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 259200)
public class RefreshTokenEntity {

    @Id
    private Long userId;

    private String value;

    private long expiresIn;

    @Builder
    private RefreshTokenEntity(Long userId, String value, long expiresIn) {
        this.userId = userId;
        this.value = value;
        this.expiresIn = expiresIn;
    }

    public static RefreshTokenEntity from(RefreshToken refreshToken) {
        return RefreshTokenEntity.builder()
                .userId(refreshToken.getUserId())
                .value(refreshToken.getValue())
                .expiresIn(refreshToken.getExpiresIn())
                .build();
    }

    public RefreshToken toDomain() {
        return RefreshToken.builder()
                .userId(userId)
                .value(value)
                .expiresIn(expiresIn)
                .build();
    }

}

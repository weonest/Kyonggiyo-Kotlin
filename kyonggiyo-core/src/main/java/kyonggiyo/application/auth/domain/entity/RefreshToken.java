package kyonggiyo.application.auth.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {

    @Id
    private Long userId;

    private String value;

    private long expiresIn;

    @Builder
    private RefreshToken(Long userId, String value, long expiresIn) {
        this.userId = userId;
        this.value = value;
        this.expiresIn = expiresIn;
    }

}

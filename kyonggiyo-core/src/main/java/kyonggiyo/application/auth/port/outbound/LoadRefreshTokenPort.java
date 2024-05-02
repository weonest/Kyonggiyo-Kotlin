package kyonggiyo.application.auth.port.outbound;

import kyonggiyo.application.auth.domain.entity.RefreshToken;

public interface LoadRefreshTokenPort {

    RefreshToken getByUserId(Long userId);

}

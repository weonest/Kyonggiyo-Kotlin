package kyonggiyo.application.auth.port.outbound;

import kyonggiyo.application.auth.domain.entity.RefreshToken;

public interface SaveRefreshTokenPort {

    void save(RefreshToken refreshToken);

}

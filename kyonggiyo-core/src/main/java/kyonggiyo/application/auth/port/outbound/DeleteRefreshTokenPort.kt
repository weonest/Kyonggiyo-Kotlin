package kyonggiyo.application.auth.port.outbound;

public interface DeleteRefreshTokenPort {

    void deleteByUserId(Long userId);

}

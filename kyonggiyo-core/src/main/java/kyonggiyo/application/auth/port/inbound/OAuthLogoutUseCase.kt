package kyonggiyo.application.auth.port.inbound;

public interface OAuthLogoutUseCase {

    void logout(Long userId);

}

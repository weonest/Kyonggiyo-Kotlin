package kyonggiyo.application.auth.port.inbound;


public interface ReissueTokenUseCase {

    TokenResponse reissueToken(String refreshToken);

}

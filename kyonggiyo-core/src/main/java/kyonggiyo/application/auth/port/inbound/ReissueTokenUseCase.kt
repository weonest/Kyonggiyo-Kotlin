package kyonggiyo.application.auth.port.inbound

interface ReissueTokenUseCase {

    fun reissueToken(refreshToken: String): TokenResponse

}

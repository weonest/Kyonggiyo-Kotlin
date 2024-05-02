package kyonggiyo.application.auth.port.inbound

interface OAuthLogoutUseCase {

    fun logout(userId: Long)

}

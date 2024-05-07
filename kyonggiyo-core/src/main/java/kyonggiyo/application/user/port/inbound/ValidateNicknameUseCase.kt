package kyonggiyo.application.user.port.inbound

interface ValidateNicknameUseCase {
    fun existByNickname(nickname: String): Boolean
}
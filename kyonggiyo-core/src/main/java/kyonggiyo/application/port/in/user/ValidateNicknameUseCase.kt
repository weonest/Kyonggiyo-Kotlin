package kyonggiyo.application.port.`in`.user

interface ValidateNicknameUseCase {
    fun existByNickname(nickname: String): Boolean
}
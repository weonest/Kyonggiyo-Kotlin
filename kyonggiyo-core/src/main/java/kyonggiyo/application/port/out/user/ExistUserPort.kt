package kyonggiyo.application.port.out.user

interface ExistUserPort {
    fun existByNickname(nickname: String): Boolean
}
package kyonggiyo.application.user.port.outbound

interface ExistUserPort {
    fun existByNickname(nickname: String): Boolean
}
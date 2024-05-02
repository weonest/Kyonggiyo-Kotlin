package kyonggiyo.application.auth.port.outbound

interface DeleteRefreshTokenPort {

    fun deleteByUserId(userId: Long)

}

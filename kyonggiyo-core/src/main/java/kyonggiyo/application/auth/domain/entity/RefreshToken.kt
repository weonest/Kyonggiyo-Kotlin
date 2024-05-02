package kyonggiyo.application.auth.domain.entity

data class RefreshToken(
        val userId: Long,
        val value: String,
        val expiresIn: Long
)

package kyonggiyo.application.auth.domain.vo

data class AccessToken(
        val value: String,
        val expiresIn: Long
)

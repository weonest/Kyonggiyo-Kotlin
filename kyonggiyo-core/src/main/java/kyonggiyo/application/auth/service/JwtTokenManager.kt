package kyonggiyo.application.auth.service

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import kyonggiyo.application.auth.domain.entity.RefreshToken
import kyonggiyo.application.auth.domain.exception.ExpiredTokenException
import kyonggiyo.application.auth.domain.exception.InvalidTokenException
import kyonggiyo.application.auth.domain.exception.TokenErrorCode
import kyonggiyo.application.auth.domain.vo.AccessToken
import kyonggiyo.application.auth.domain.vo.AuthInfo
import kyonggiyo.common.property.JwtProperties
import kyonggiyo.application.user.domain.vo.Role
import org.json.JSONObject
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import java.util.random.RandomGenerator

@Component
class JwtTokenManager(
        private val jwtProperties: JwtProperties
) : TokenManager {

    override fun generateAccessToken(userId: Long, role: Role): AccessToken {
        val currentTimeMillis = System.currentTimeMillis()
        val primaryNum = RandomGenerator.getDefault().nextInt()
        val expiresIn = currentTimeMillis + jwtProperties.accessTokenExpireTime * 1000L

        val claims = Jwts.claims()
                .add(jwtProperties.claimId, userId)
                .add(jwtProperties.claimRole, role)
                .build()

        val accessToken = Jwts.builder()
                .claims(claims)
                .issuer(primaryNum.toString())
                .issuedAt(Date(currentTimeMillis))
                .expiration(Date(expiresIn))
                .signWith(decodedKey(jwtProperties.secretKey), SignatureAlgorithm.HS512)
                .compact()

        return AccessToken(accessToken, jwtProperties.accessTokenExpireTime)
    }

    override fun generateRefreshToken(userId: Long): RefreshToken {
        val currentTimeMillis = System.currentTimeMillis()
        val primaryNum = RandomGenerator.getDefault().nextInt()
        val expiresIn = currentTimeMillis + jwtProperties!!.refreshTokenExpireTime * 1000L

        val refreshToken = Jwts.builder()
                .issuer(primaryNum.toString())
                .issuedAt(Date(currentTimeMillis))
                .expiration(Date(expiresIn))
                .signWith(decodedKey(jwtProperties.secretKey), SignatureAlgorithm.HS512)
                .compact()

        return RefreshToken(userId = userId,
                value = refreshToken,
                expiresIn = jwtProperties.refreshTokenExpireTime)
    }

    override fun validate(token: String) {
        try {
            Jwts.parser()
                    .setSigningKey(decodedKey(jwtProperties!!.secretKey))
                    .build()
                    .parseClaimsJws(token)
        } catch (expiredJwtException: ExpiredJwtException) {
            throw ExpiredTokenException(TokenErrorCode.EXPIRED_TOKEN_EXCEPTION)
        } catch (exception: Exception) {
            throw InvalidTokenException(TokenErrorCode.INVALID_TOKEN_EXCEPTION)
        }
    }

    override fun extract(token: String): AuthInfo {
        val claims = token.split("\\.")[1]
        val claimsBytes = Decoders.BASE64URL.decode(claims)
        val decodedClaims = String(claimsBytes, StandardCharsets.UTF_8)
        val jsonObject = JSONObject(decodedClaims)
        return AuthInfo(jsonObject.getLong("id"), Role.valueOf(jsonObject.get("role").toString()))
    }

    companion object {
        private fun decodedKey(key: String): Key {
            return Keys.hmacShaKeyFor(
                    key.toByteArray(StandardCharsets.UTF_8)
            )
        }
    }

}

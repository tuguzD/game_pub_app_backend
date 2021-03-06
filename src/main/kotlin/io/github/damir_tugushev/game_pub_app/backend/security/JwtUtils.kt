package io.github.damir_tugushev.game_pub_app.backend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import kotlin.time.Duration.Companion.hours

@Service
class JwtUtils {
    companion object {
        private const val secretKey = "[vw-935unyp39qi[dk2j4pewjcyl2k-305w][cwm35u23"
    }

    fun extractLogin(token: String): String = extractClaim(token, Claims::getSubject)

    fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    fun <T> extractClaim(token: String, claimsResolver: Claims.() -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateToken(userDetails: UserDetails): String {
        return createToken(mutableMapOf(), userDetails.username)
    }

    fun validateToken(token: String, userDetails: UserDetails) =
        extractLogin(token) == userDetails.username && !isTokenExpired(token)

    private fun createToken(claims: MutableMap<String, out Any>, subject: String): String = Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(Date())
        .setExpiration(Date(System.currentTimeMillis() + 1.hours.inWholeMilliseconds))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact()

    private fun extractAllClaims(token: String): Claims =
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body

    private fun isTokenExpired(token: String) = extractExpiration(token).before(Date())
}

package com.ascenders.securedapi.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import java.util.HashMap
import javax.crypto.SecretKey

@Component
class JwtUtils {

    private var secret = ""

    init {
        val key : SecretKey = Jwts.SIG.HS256.key().build()

        secret = Encoders.BASE64URL.encode(key.encoded)
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap() // to be filled....
        return createToken(claims, userDetails.username)
    }

    fun createToken(claims: Map<String, Any>, username: String): String {
        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(getKey())
            .compact()
    }

    fun extractUsername(token: String): String? {
        return getClaim(token, Claims::getSubject)
    }

    fun hasExpired(token: String): Boolean {
        val expireDate = getClaim(token, Claims::getExpiration)

        return expireDate == Date(System.currentTimeMillis())
    }

    fun isValidToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)

        return userDetails.username.equals(username) && !hasExpired(token)
    }

    fun <T> getClaim(token: String, claimResolver: (Claims) -> T): T {
        val claims = getAllClaims(token)
        return claimResolver(claims)
    }

    fun getAllClaims(token: String): Claims {
        return Jwts.parser()
                .verifyWith(getKey()).build()
                .parseSignedClaims(token).payload
    }

    fun getKey(): SecretKey {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret))
    }
}
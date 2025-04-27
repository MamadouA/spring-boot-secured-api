package com.ascenders.securedapi.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
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

    fun generateToken(username: String): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, username)
    }

    fun createToken(claims: Map<String, Any>, username: String): String {
        println(secret)

        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(getKey())
            .compact()
    }

    fun getKey(): SecretKey {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret))
    }
}
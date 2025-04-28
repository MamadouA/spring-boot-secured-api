package com.ascenders.securedapi.controller

import com.ascenders.securedapi.auth.AuthUser
import com.ascenders.securedapi.util.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Autowired
    lateinit var jwtUtils: JwtUtils
    @Autowired
    lateinit var userDetailsService: UserDetailsService
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @PostMapping("login")
    fun login(@RequestBody @Validated authUser: AuthUser): ResponseEntity<String>  {

        try {
            val authentication = UsernamePasswordAuthenticationToken(authUser.username, authUser.password)

            this.authenticationManager.authenticate(authentication)

            return ResponseEntity.ok(jwtUtils.generateToken(userDetailsService.loadUserByUsername(authUser.username)))
        }
        catch (e: AuthenticationException)  {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username or password incorrect!")
        }
    }

    @GetMapping
    fun greeting() = "Hello world!"

    @GetMapping("about")
    fun about() = "This is a secured api."
}
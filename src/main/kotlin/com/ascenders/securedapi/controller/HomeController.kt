package com.ascenders.securedapi.controller

import com.ascenders.securedapi.auth.AuthUser
import com.ascenders.securedapi.util.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @PostMapping("login")
    fun login(@RequestBody authUser: AuthUser): String?  {

        return if(authUser.username != null) jwtUtils.generateToken(authUser.username!!) else ""
    }

    @GetMapping
    fun greeting() = "Hello world!"

    @GetMapping("about")
    fun about() = "This is a secured api."
}
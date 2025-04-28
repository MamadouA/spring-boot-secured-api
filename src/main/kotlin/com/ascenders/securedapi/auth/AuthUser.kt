package com.ascenders.securedapi.auth

import org.jetbrains.annotations.NotNull
import org.springframework.stereotype.Component

@Component
 class AuthUser {

     @NotNull
    private lateinit var _username: String

    @NotNull
    private lateinit var _password: String

    var username: String
        get() = _username
        set(value) {
            _username = value
        }

    var password: String
        get() = _password
        set(value) {
            _password = value
        }
}
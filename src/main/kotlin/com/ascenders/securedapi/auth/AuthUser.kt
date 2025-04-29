package com.ascenders.securedapi.auth

import org.springframework.stereotype.Component

@Component
 class AuthUser {

    private lateinit var _username: String
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
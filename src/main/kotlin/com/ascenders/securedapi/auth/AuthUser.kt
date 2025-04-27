package com.ascenders.securedapi.auth

import org.springframework.stereotype.Component

@Component
data class AuthUser(private var _username: String?, private var _password: String?) {

    var username: String?
        get() = _username
        set(value) {
            _username = value
        }

    var password: String?
        get() = _password
        set(value) {
            _password = value
        }
}
package com.ascenders.securedapi.advice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableHandler(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect data format")
    }

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationExceptionHandler(e: AuthenticationException): ResponseEntity<String> {
        logger.error("Authentication exception", e)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username or password incorrect!")
    }
}
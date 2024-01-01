package com.example.taskzz.login.domain.model

@JvmInline
value class AuthToken(val value: String)

@JvmInline
value class RefreshToken(val value: String)

//for authenticating network requests
data class Token(
    val authToken: AuthToken,
    val refreshToken: RefreshToken
)

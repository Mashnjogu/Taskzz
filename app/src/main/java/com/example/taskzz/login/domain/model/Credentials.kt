package com.example.taskzz.login.domain.model

@JvmInline
value class Email(private val email: String)

@JvmInline
value class Password(private val password: String)

data class Credentials(
    val email: Email,
    val password: Password
)
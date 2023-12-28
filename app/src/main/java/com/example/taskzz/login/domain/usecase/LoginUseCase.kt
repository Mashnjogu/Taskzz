package com.example.taskzz.login.domain.usecase

import com.example.taskzz.login.domain.model.LoginResult

@JvmInline
value class Email(private val email: String)

@JvmInline
value class Password(private val password: String)

interface LoginUseCase {
    suspend operator fun invoke (email: Email, password: Password): LoginResult
}
package com.example.taskzz.login.domain.usecase

import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password


interface CredentialsLoginUseCase {
    suspend operator fun invoke (credentials: Credentials): LoginResult
}
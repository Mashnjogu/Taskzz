package com.example.taskzz.login.domain.usecase

import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResult
import kotlinx.coroutines.delay
import javax.inject.Inject

class DemoCredentialsLoginUseCase : CredentialsLoginUseCase{
    override suspend fun invoke(credentials: Credentials): LoginResult {
        delay(3000)
        return LoginResult.Failure.InvalidCredentials
    }

}
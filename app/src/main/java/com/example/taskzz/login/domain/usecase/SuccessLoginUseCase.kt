package com.example.taskzz.login.domain.usecase

import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResult

class SuccessLoginUseCase: CredentialsLoginUseCase {
    override suspend fun invoke(credentials: Credentials): LoginResult {
        return LoginResult.Success
    }

}
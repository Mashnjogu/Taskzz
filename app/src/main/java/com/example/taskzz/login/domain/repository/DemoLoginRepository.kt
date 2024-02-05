package com.example.taskzz.login.domain.repository

import com.example.taskzz.core.data.Result
import com.example.taskzz.login.domain.model.AuthToken
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResponse
import com.example.taskzz.login.domain.model.RefreshToken
import com.example.taskzz.login.domain.model.Token

/*
Fake implementation of LoginRepository
 */
class DemoLoginRepository: LoginRepository {
    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        val defaultToken = Token(
            AuthToken(""),
            RefreshToken("")
        )
        val defaultResponse = LoginResponse(defaultToken)
        return Result.Success(defaultResponse)
    }

    override suspend fun storeAuthToken(authToken: String) {
        TODO("Not yet implemented")
    }

}
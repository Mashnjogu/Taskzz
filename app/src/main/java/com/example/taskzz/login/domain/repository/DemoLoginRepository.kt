package com.example.taskzz.login.domain.repository

import com.example.taskzz.core_data.Result
import com.example.taskzz.login.domain.model.AuthToken
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResponse
import com.example.taskzz.login.domain.model.RefreshToken
import com.example.taskzz.login.domain.model.Token
import javax.inject.Inject

/*
Fake implementation of LoginRepository
 */
class DemoLoginRepository @Inject constructor(): LoginRepository {
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
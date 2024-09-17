package com.example.taskzz.login.domain.repository

import com.example.taskzz.core_data.Result
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResponse

interface LoginRepository {
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>

    suspend fun storeAuthToken(authToken: String)
}
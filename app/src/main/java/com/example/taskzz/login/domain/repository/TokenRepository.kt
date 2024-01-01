package com.example.taskzz.login.domain.repository

import com.example.taskzz.login.domain.model.Token

interface TokenRepository {

    fun storeToken(authToken: Token)

    fun fetchToken(): Token
}
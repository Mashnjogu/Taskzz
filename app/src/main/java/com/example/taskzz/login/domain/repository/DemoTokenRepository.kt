package com.example.taskzz.login.domain.repository

import com.example.taskzz.login.domain.model.Token


class DemoTokenRepository: TokenRepository {
    override fun storeToken(authToken: Token) {
        TODO("Not yet implemented")
    }

    override fun fetchToken(): Token? {
        return null
    }


}
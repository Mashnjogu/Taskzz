package com.example.taskzz.login.domain.repository

import com.example.taskzz.login.domain.model.Token
import javax.inject.Inject


class DemoTokenRepository @Inject constructor(): TokenRepository {
    override fun storeToken(authToken: Token) {

    }

    override fun fetchToken(): Token? {
        return null
    }


}
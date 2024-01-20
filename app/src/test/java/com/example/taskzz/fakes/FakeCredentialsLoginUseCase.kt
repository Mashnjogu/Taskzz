package com.example.taskzz.fakes

import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.usecase.CredentialsLoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeCredentialsLoginUseCase {

    val mock: CredentialsLoginUseCase = mockk()

    fun mockLoginResultsForCredentials(
        credentials: Credentials,
        result: LoginResult
    ){
        coEvery {
            mock.invoke(credentials)
        } returns result
    }
}
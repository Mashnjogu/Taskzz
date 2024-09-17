package com.example.taskzz.fakes

import com.example.taskzz.core_data.Result
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResponse
import com.example.taskzz.login.domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class FakeLoginRepository{
    val mock: LoginRepository = mockk()

    fun mockLoginWithCredentials(
        credentials: Credentials,
        results: Result<LoginResponse>
    ){
        coEvery {
            mock.login(credentials)
        }returns results

    }

    fun verifyNoLoginCall() {
        coVerify(exactly = 0) {
            mock.login(any())
        }
    }
}
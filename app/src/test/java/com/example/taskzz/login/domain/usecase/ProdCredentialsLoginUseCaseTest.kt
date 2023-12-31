package com.example.taskzz.login.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.fakes.FakeLoginRepository
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.InvalidCredentialsException
import com.example.taskzz.login.domain.model.LoginResponse
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProdCredentialsLoginUseCaseTest {


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSuccessfulLogin() = runTest{
        val inputCredentials = Credentials(
            email = Email("test@learntdd@gmail.com"),
            password = Password("hjk4*jk3")
        )

        val mockResponse = LoginResponse(
            authToken = "Success"
        )

        val mockResult = Result.Success(mockResponse)

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(
                inputCredentials,
                mockResult
            )
        }

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock)
        val result = useCase(inputCredentials)
        assertThat(result).isEqualTo(LoginResult.Success)
    }

    suspend fun fetchData(): String{
        delay(3000L)
        return "Hello Dennis"
    }

    @Test
    fun dataShouldGreetDennis() = runTest{
        val data = fetchData()
        assertThat(data).isEqualTo("Hello Dennis")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUnknownLoginFailure() = runTest{
        val inputCredentials = Credentials(
            email = Email("test@learntdd@gmail.com"),
            password = Password("hjk4*jk3")
        )

        val loginResponse: Result<LoginResponse> = Result.Error(
            Throwable("Dennis Fucked up")
        )

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(
                inputCredentials,
                loginResponse
            )
        }

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock)
        val result = useCase(inputCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.Unknown)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInvalidCredentialsLogin() = runTest{
        val inputCredentials = Credentials(
            email = Email("test@learntdd@gmail.com"),
            password = Password("hjk4*jk3")
        )

        val loginResponse: Result<LoginResponse> = Result.Error(
            InvalidCredentialsException()
        )

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(
                inputCredentials,
                loginResponse
            )
        }

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock)
        val result = useCase(inputCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.InvalidCredentials)
    }
}



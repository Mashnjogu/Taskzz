package com.example.taskzz.login.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.fakes.FakeLoginRepository
import com.example.taskzz.fakes.FakeTokenRepository
import com.example.taskzz.login.domain.model.AuthToken
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.InvalidCredentialsException
import com.example.taskzz.login.domain.model.LoginResponse
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password
import com.example.taskzz.login.domain.model.RefreshToken
import com.example.taskzz.login.domain.model.Token
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProdCredentialsLoginUseCaseTest {

    private val defaultCredentials = Credentials(
        email = Email("test@learntdd@gmail.com"),
        password = Password("hjk4*jk3")
    )

    private val defaultToken = Token(
        authToken = AuthToken("Auth"),
        refreshToken = RefreshToken("Refresh")
    )

    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var tokenRepository: FakeTokenRepository

    @Before
    fun setup(){
        loginRepository = FakeLoginRepository()
        tokenRepository = FakeTokenRepository()
    }

    private val defaultLoginResponse = LoginResponse(token = defaultToken)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSuccessfulLogin() = runTest{

        val loginResponse = Result.Success(
            LoginResponse(token = defaultToken)
        )

        loginRepository.mockLoginWithCredentials(defaultCredentials, loginResponse)

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock, tokenRepository.mock)
        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Success)

        tokenRepository.verifyTokenStored(defaultToken)
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


        val loginResponse: Result<LoginResponse> = Result.Error(
            Throwable("Dennis Fucked up")
        )


        loginRepository.mockLoginWithCredentials( defaultCredentials,loginResponse)


        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock
        )
        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.Unknown)

        tokenRepository.verifyNoTokenStored()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testInvalidCredentialsLogin() = runTest{

        val loginResponse: Result<LoginResponse> = Result.Error(
            InvalidCredentialsException()
        )

        loginRepository.mockLoginWithCredentials(
                defaultCredentials,
                loginResponse
            )


        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock, tokenRepository.mock)
        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.InvalidCredentials)

        tokenRepository.verifyNoTokenStored()
    }
}



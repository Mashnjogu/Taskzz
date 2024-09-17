package com.example.taskzz.login.domain.usecase

import com.example.taskzz.core_data.Result
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.InvalidCredentialsException
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.repository.TokenRepository
import com.example.taskzz.login.domain.repository.LoginRepository
import javax.inject.Inject

/*
Concrete implementation of [CredentialsLoginUsecase] that will request login in via
login repository
 */
class ProdCredentialsLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
): CredentialsLoginUseCase {
    override suspend fun invoke(credentials: Credentials): LoginResult {

        val validationResult = validateCredentials(credentials)

        if (validationResult != null){
            return validationResult
        }

        val repoResult = loginRepository.login(credentials)

        return when(repoResult){
            is Result.Success -> {
                tokenRepository.storeToken(repoResult.data.token)
                //store auth token
                return LoginResult.Success
            }
            is Result.Error -> {
                loginResultForFailure(repoResult)
            }
        }
    }

    private fun validateCredentials(credentials: Credentials): LoginResult?{
        val emptyEmail = credentials.email.value.isEmpty()
        val emptyPassword = credentials.password.value.isEmpty()

        return if (emptyEmail || emptyPassword) {
            return LoginResult.Failure.EmptyCredentials(
                emptyEmail = emptyEmail,
                emptyPassword = emptyPassword
            )
        }else{
            null
        }
    }

    private fun loginResultForFailure(repoResult: Result.Error) =
        when (repoResult.error) {
            is InvalidCredentialsException -> {
                LoginResult.Failure.InvalidCredentials
            }

            else -> {
                LoginResult.Failure.Unknown
            }
        }

}
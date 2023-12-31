package com.example.taskzz.login.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.InvalidCredentialsException
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.repository.LoginRepository

/*
Concrete implementation of [CredentialsLoginUsecase] that will request login in via
login repository
 */
class ProdCredentialsLoginUseCase(
    private val loginRepository: LoginRepository
): CredentialsLoginUseCase {
    override suspend fun invoke(credentials: Credentials): LoginResult {
        val repoResult = loginRepository.login(credentials)

        return when(repoResult){
            is Result.Success -> {
                //store auth token
                return LoginResult.Success
            }
            is Result.Error -> {
                loginResultForFailure(repoResult)
            }
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
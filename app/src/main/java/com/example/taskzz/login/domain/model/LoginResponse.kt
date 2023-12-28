package com.example.taskzz.login.domain.model

//info returned from any successful Login request
data class LoginResponse(
    val authToken: String
)

sealed class LoginResult{
    object Success: LoginResult()


    sealed class Failure: LoginResult(){

        object InvalidCredentials: Failure()

        //This will be returned for unknown exceptions
        object Unknown: Failure()


    }

}



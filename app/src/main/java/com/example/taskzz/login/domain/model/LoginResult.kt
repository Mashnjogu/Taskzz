package com.example.taskzz.login.domain.model

sealed class LoginResult{
    object Success: LoginResult()


    sealed class Failure: LoginResult(){

        object InvalidCredentials: Failure()

        //This will be returned for unknown exceptions
        object Unknown: Failure()


    }

}
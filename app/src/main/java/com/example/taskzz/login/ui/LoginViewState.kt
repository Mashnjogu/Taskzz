package com.example.taskzz.login.ui

import com.example.taskzz.login.domain.model.Credentials


sealed class LoginViewState(
    open val credentials: Credentials,
    open val buttonEnabled: Boolean = true
){

    /*
    initial state of the screen with nothing as the input
     */
    object Initial: LoginViewState(
        credentials = Credentials()
    )

    /*
    state of the screen as the user is entering email information
     */
    data class Active(
        override val credentials: Credentials
    ): LoginViewState(
        credentials = credentials
    )

    /*
    state of the screen when the user is attempting to login
     */
    data class Submitting(
        override val credentials: Credentials
    ): LoginViewState(
        credentials = credentials,
        buttonEnabled = false
    )

    /*
    state of the screen when there is an error logging in
     */
    data class SubmissionError(
        override val credentials: Credentials,
        val errorMessage: String
    ): LoginViewState(
        credentials = credentials
    )
    data class InputError(
        override val credentials: Credentials,
        val emailInputErrorMessage: String?,
        val passwordInputErrorMessage: String?
    ): LoginViewState(
        credentials = credentials
    )

}

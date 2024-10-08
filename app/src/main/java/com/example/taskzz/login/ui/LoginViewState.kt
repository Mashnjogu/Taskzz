package com.example.taskzz.login.ui

import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.core.ui.components.UiText


sealed class LoginViewState(
    open val credentials: Credentials,
    open val inputsEnabled: Boolean = true
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
        override val credentials: Credentials,
        val emailInputErrorMessage: UiText? = null,
        val passwordInputErrorMessage: UiText? = null
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
        inputsEnabled = false
    )

    /*
    state of the screen when there is an error logging in
     */
    data class SubmissionError(
        override val credentials: Credentials,
        val errorMessage: UiText
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

    object Completed: LoginViewState(
        credentials = Credentials(),
        inputsEnabled = false
    )

}

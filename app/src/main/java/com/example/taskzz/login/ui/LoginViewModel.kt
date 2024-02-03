package com.example.taskzz.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.R
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password
import com.example.taskzz.login.domain.usecase.CredentialsLoginUseCase
import com.example.taskzz.ui.components.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val credentialsLoginUseCase: CredentialsLoginUseCase
): ViewModel() {

    private val _viewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState.Initial)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun emailChanged(email: String) {
        /*
        whenever the email changes, it goes automatically to the active state
         */
        val currentCredentials = _viewState.value.credentials
        val currentEmailErrorMessage = (_viewState.value as? LoginViewState.Active)?.emailInputErrorMessage
        val currentPasswordErrorMessage = (_viewState.value as? LoginViewState.Active)?.passwordInputErrorMessage

        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedEmail(email),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = currentPasswordErrorMessage
        )
    }
    fun passwordChanged(password: String) {
        /*
       whenever the password changes, it goes automatically to the active state
        */
        val currentCredentials = _viewState.value.credentials
        val currentEmailErrorMessage = (_viewState.value as? LoginViewState.Active)?.emailInputErrorMessage

        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedPassword(password),
            emailInputErrorMessage = currentEmailErrorMessage,
            passwordInputErrorMessage = null
        )
    }

    fun logInButtonClicked(){
        val currentCredentials = _viewState.value.credentials

        /*
        if the credentials are not valid, don't do anything
         */
        if (!validateCredentials(currentCredentials)){
            return
        }

        _viewState.value = LoginViewState.Submitting(credentials = currentCredentials)

        viewModelScope.launch{
            val loginResult = credentialsLoginUseCase.invoke(currentCredentials)

            _viewState.value = when(loginResult){
                is LoginResult.Failure.InvalidCredentials -> {
                    LoginViewState.SubmissionError(
                        credentials = currentCredentials,
                        errorMessage = UiText.ResourceText(R.string.err_invalid_credentials)
                    )
                }
                is LoginResult.Failure.Unknown -> {
                    LoginViewState.SubmissionError(
                        credentials = currentCredentials,
                        errorMessage = UiText.ResourceText(R.string.error_login_failure)
                    )
                }
                is LoginResult.Failure.EmptyCredentials -> {
                    loginResult.toLoginViewState(credentials = currentCredentials)
                }
                else -> _viewState.value
            }
        }
    }

    fun signUpButtonClicked(){
        TODO()
    }

    /*
    Given [credentials] returns true if the credentials are valid, false otherwise.
    If not update state correctly
     */
    private fun validateCredentials(credentials: Credentials): Boolean{
        val hasEmail = credentials.email.value.isNotEmpty()
        val hasPassword = credentials.password.value.isNotEmpty()

        _viewState.value = LoginViewState.Active(
            credentials = credentials,
            emailInputErrorMessage = if (hasEmail){
                                                  null
                                                  }else{
                                                        UiText.ResourceText(R.string.err_empty_email)
                                                       },
            passwordInputErrorMessage = if (hasPassword){
                                                        null
                                                        }else{
                                                            UiText.ResourceText(R.string.err_empty_password)
            }
        )

        return hasEmail && hasPassword
    }



}


private fun Credentials.withUpdatedEmail(email: String): Credentials{
    return this.copy(email = Email(email))
}

private fun Credentials.withUpdatedPassword(password: String): Credentials{
    return this.copy(password = Password(password))
}


private fun LoginResult.Failure.EmptyCredentials.toLoginViewState(credentials: Credentials): LoginViewState {
    return LoginViewState.Active(
        credentials = credentials,
        emailInputErrorMessage = UiText.ResourceText(R.string.err_empty_email).takeIf {
            this.emptyEmail
        },
        passwordInputErrorMessage = UiText.ResourceText(R.string.err_empty_password).takeIf {
            this.emptyPassword
        }
    )
}





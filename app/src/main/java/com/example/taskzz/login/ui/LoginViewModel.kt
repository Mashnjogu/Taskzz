package com.example.taskzz.login.ui

import androidx.lifecycle.ViewModel
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.Password
import com.example.taskzz.login.domain.usecase.CredentialsLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedEmail(email)
        )
    }
    fun passwordChanged(password: String) {
        /*
       whenever the password changes, it goes automatically to the active state
        */
        val currentCredentials = _viewState.value.credentials
        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedPassword(password)
        )
    }

    fun logInButtonClicked(){
        TODO()
    }

    fun signUpButtonClicked(){
        TODO()
    }

    private fun Credentials.withUpdatedEmail(email: String): Credentials{
        return this.copy(email = Email(email))
    }

    private fun Credentials.withUpdatedPassword(password: String): Credentials{
        return this.copy(password = Password(password))
    }

}
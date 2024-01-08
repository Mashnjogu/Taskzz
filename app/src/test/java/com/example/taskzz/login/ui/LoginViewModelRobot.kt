package com.example.taskzz.login.ui

import com.example.taskzz.fakes.FakeCredentialsLoginUseCase
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.LoginResult
import com.google.common.truth.Truth.assertThat

/*
Basic understanding, It is an abstraction layer from the viewmodel and the test classes.
Helps to avoid changing all the test cases incase the viewmodel changes.
 */
class LoginViewModelRobot {

    private val fakeCredentialsLoginUseCase = FakeCredentialsLoginUseCase()
    private lateinit var viewModel: LoginViewModel

    fun buildViewModel() = apply {
        viewModel = LoginViewModel(
            credentialsLoginUseCase = fakeCredentialsLoginUseCase.mock
        )
    }

    fun mockLoginResultForCredentials(
        credentials: Credentials,
        result: LoginResult
    ) = apply{
      fakeCredentialsLoginUseCase.mockLoginResultsForCredentials(credentials, result)
    }

    fun enterEmail(email: String) = apply{
        viewModel.emailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLoginButton() = apply {
        viewModel.signInButtonClicked()
    }

    fun clickSignUpButton() = apply {
        viewModel.signUpButtonClicked()
    }

    fun assertViewState(expectedViewState: LoginViewState)= apply {
        assertThat(viewModel.viewState).isEqualTo(expectedViewState)
    }
}
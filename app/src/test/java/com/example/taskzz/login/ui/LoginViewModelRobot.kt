package com.example.taskzz.login.ui

import app.cash.turbine.test
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
      fakeCredentialsLoginUseCase.mockLoginResultsForCredentials(
          credentials = credentials,
          result = result
      )
    }

    fun enterEmail(email: String) = apply{
        viewModel.emailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLoginButton() = apply {
        viewModel.logInButtonClicked()
    }

    fun clickSignUpButton() = apply {
        viewModel.signUpButtonClicked()
    }

    fun assertViewState(expectedViewState: LoginViewState)= apply {
        assertThat(viewModel.viewState).isEqualTo(expectedViewState)
    }

    suspend fun assertViewStatesAfterAction(
        action: LoginViewModelRobot.() -> Unit,
        states: List<LoginViewState>
    ) = apply {
        viewModel.viewState.test {
            action()
            for (state in states) {
                assertThat(awaitItem()).isEqualTo(state)
            }
            cancel()
        }
    }
//1:08:39

}
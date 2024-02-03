package com.example.taskzz.login.ui

import com.example.taskzz.CoroutineTestRule
import com.example.taskzz.R
import com.example.taskzz.ThreadExceptionTestRule
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password
import com.example.taskzz.login.domain.usecase.ProdCredentialsLoginUseCase
import com.example.taskzz.ui.components.UiText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

    val defaultCredentials = Credentials(
        Email("test56@gmail.com"),
        Password("hsjs#sms^")
    )

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @get:Rule
    val uncaughtExceptionHandlerRule = ThreadExceptionTestRule()

    @Before
    fun setup(){
        testRobot = LoginViewModelRobot()
    }

    /*
    we begin with testing our loginstate
     */
    @Test
    fun testInitialState()= runTest{
//        testRobot.buildViewModel().assertViewState(LoginViewState.Initial)

        testRobot.buildViewModel().expectViewStates(
            action = {}, states = listOf(LoginViewState.Initial))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testUpdateCredentials() = runTest{
        val testEmail = "test@test.com"
        val testPassword = "snsnRnsns"

        val initialState = LoginViewState.Initial
        val emailEnteredState =  LoginViewState.Active(
            Credentials(email = Email(testEmail))
        )
        val passwordEnteredState = LoginViewState.Active(
            Credentials(email = Email(testEmail), password = Password(testPassword))
        )
        val expectedViewStates = listOf(initialState, emailEnteredState, passwordEnteredState)

        testRobot.buildViewModel()
            .expectViewStates(
                states = expectedViewStates,
                action = {
                    this.enterEmail(testEmail)
                    this.enterPassword(testPassword)
                }
            )

    }

    @Test
    fun testSubmitInvalidCredentials() = runTest{
        val testEmail = "test@test.com"
        val testPassword = "snsnRnsns"

        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword)
        )

        val initialState = LoginViewState.Initial
        val emailEnteredState =  LoginViewState.Active(
            Credentials(email = Email(testEmail))
        )
        val passwordEnteredState = LoginViewState.Active(
            credentials = completedCredentials
        )
        val submittingState  = LoginViewState.Submitting(
            credentials = completedCredentials
        )
        val submissionErrorState = LoginViewState.SubmissionError(
            credentials = completedCredentials,
            errorMessage = UiText.ResourceText(R.string.err_invalid_credentials)
        )
        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            passwordEnteredState,
            submittingState,
            submissionErrorState
        )

        testRobot.buildViewModel()
            .mockLoginResultForCredentials(
                credentials = completedCredentials,
                result = LoginResult.Failure.InvalidCredentials
            )
            .expectViewStates(
                states = expectedViewStates,
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLoginButton()
                }
            )

    }

    @Test
    fun testUnkownLoginFailure() = runTest{
        val testEmail = "test@test.com"
        val testPassword = "snsnRnsns"

        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword)
        )

        val initialState = LoginViewState.Initial
        val emailEnteredState =  LoginViewState.Active(
            Credentials(email = Email(testEmail))
        )
        val passwordEnteredState = LoginViewState.Active(
            credentials = completedCredentials
        )
        val submittingState  = LoginViewState.Submitting(
            credentials = completedCredentials
        )
        val submissionErrorState = LoginViewState.SubmissionError(
            credentials = completedCredentials,
            errorMessage = UiText.ResourceText(R.string.error_login_failure)
        )
        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            passwordEnteredState,
            submittingState,
            submissionErrorState
        )

        testRobot.buildViewModel()
            .mockLoginResultForCredentials(
                credentials = completedCredentials,
                result = LoginResult.Failure.Unknown
            )
            .expectViewStates(
                states = expectedViewStates,
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLoginButton()
                }
            )

    }

    @Test
    fun testSubmitWithoutCredentials() = runTest{

        val credentials = Credentials()
        val initialState = LoginViewState.Initial
//        val submittingState = LoginViewState.Submitting(credentials = credentials)
        val invalidInputState = LoginViewState.Active(
            credentials = credentials,
            emailInputErrorMessage = UiText.ResourceText(R.string.err_empty_email),
            passwordInputErrorMessage = UiText.ResourceText(R.string.err_empty_password)
        )

        testRobot
            .buildViewModel()
            .expectViewStates(
                states = listOf(
                    initialState,
//                    submittingState,
                    invalidInputState),
                action = {
                    clickLoginButton()
                }
            )
            .mockLoginResultForCredentials(
                credentials = credentials,
                result = LoginResult.Failure.EmptyCredentials(
                    emptyEmail = true,
                    emptyPassword = true)
            )
    }

    @Test
    fun testClearErrorsAfterInput() = runTest{
        val testEmail = "test@test.com"
        val testPassword = "snsnRnsns"

        val credentials = Credentials()

        val initialState = LoginViewState.Initial
        val submittingState = LoginViewState.Submitting(credentials = credentials)
        val invalidInputState = LoginViewState.Active(
            credentials = credentials,
            emailInputErrorMessage = UiText.ResourceText(R.string.err_empty_email),
            passwordInputErrorMessage = UiText.ResourceText(R.string.err_empty_password)
        )

        val emailInputState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail)),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = UiText.ResourceText(R.string.err_empty_password)
        )

        val passwordInputState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail), password = Password(testPassword)),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = null
        )

        testRobot
            .buildViewModel()
            .expectViewStates(
                states = listOf(
                    initialState,
//                    submittingState,
                    invalidInputState,
                    emailInputState,
                    passwordInputState,

                ),
                action = {
                    clickLoginButton()
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                }
            )
            .mockLoginResultForCredentials(
                credentials = credentials,
                result = LoginResult.Failure.EmptyCredentials(
                    emptyEmail = true,
                    emptyPassword = true)
            )
    }


}
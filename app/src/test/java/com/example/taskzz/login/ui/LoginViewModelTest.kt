package com.example.taskzz.login.ui

import com.example.taskzz.CoroutineTestRule
import com.example.taskzz.R
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password
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

        testRobot.buildViewModel().assertViewStatesAfterAction(
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
            .assertViewStatesAfterAction(
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
            .assertViewStatesAfterAction(
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
            .assertViewStatesAfterAction(
                states = expectedViewStates,
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLoginButton()
                }
            )

    }
}
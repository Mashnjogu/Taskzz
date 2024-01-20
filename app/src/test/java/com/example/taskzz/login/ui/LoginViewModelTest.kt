package com.example.taskzz.login.ui

import com.example.taskzz.R
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.LoginResult
import com.example.taskzz.login.domain.model.Password
import com.example.taskzz.ui.components.UiText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

    val defaultCredentials = Credentials(
        Email("test56@gmail.com"),
        Password("hsjs#sms^")
    )

    @Before
    fun setup(){
        testRobot = LoginViewModelRobot()
    }

    /*
    we begin with testing our loginstate
     */
    @Test
    fun testInitialState(){
        testRobot.buildViewModel().assertViewState(LoginViewState.Initial)
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

//    @Test
//    fun testInvalidCredentialLogin(){
//        val credentials = defaultCredentials
//
//        testRobot.buildViewModel()
//            .enterEmail(credentials.email.value)
//            .enterPassword(credentials.password.value)
//            .clickLoginButton()
//            .mockLoginResultForCredentials(
//                credentials = credentials,
//                result = LoginResult.Failure.InvalidCredentials
//            )
//            .assertViewState(LoginViewState.SubmissionError(
//                credentials = credentials,
//                errorMessage = UiText.ResourceText(R.string.err_invalid_credentials)
//            ))
//    }
}
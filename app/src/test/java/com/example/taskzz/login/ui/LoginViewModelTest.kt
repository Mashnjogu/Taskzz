package com.example.taskzz.login.ui

import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.Password
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

    @Test
    fun testUpdateCredentials(){
        val credentials = defaultCredentials
        testRobot.buildViewModel()
            .enterEmail(credentials.email.value)
            .enterPassword(credentials.password.value)
            .assertViewState(LoginViewState.Active(credentials))
    }
}
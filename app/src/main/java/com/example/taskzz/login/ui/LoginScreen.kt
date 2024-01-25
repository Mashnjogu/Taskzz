package com.example.taskzz.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
){
    val viewState = viewModel.viewState.collectAsState()

    

    LoginContent(
        loginViewState = viewState.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClicked = viewModel::logInButtonClicked,
        onSignUpClicked = viewModel::signUpButtonClicked
    )
}
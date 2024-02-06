package com.example.taskzz.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginCompleted: () -> Unit
){
    val viewState = viewModel.viewState.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    
    SideEffect {
        coroutineScope.launch {
            viewModel.loginCompletedChannel.receive()
        }
        onLoginCompleted.invoke()
    }


    LoginContent(
        loginViewState = viewState.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClicked = viewModel::logInButtonClicked,
        onSignUpClicked = viewModel::signUpButtonClicked
    )
}
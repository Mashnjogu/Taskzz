package com.example.taskzz.login.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskzz.destinations.LoginScreenDestination
import com.example.taskzz.destinations.TaskListScreenDestination
import com.example.taskzz.tasklist.ui.TaskListScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@Destination(
    start = true
)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: LoginViewModel = hiltViewModel(),
//    onLoginCompleted: () -> Unit
){
    val viewState = viewModel.viewState.collectAsState()

    println("LoginScreen: The viewState is $viewState")

    //if the viewState changes, the LaunchedEffect is recalled
    DisposableEffect(viewState.value){
        if (viewState.value is LoginViewState.Completed){
            navigator.navigate(TaskListScreenDestination){
                this.popUpTo(
                    LoginScreenDestination.route
                ){
                    this.inclusive = true
                }
            }


        }

        onDispose {  }
    }


    val context = LocalContext.current

    LoginContent(
        loginViewState = viewState.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClicked = viewModel::logInButtonClicked,
        onSignUpClicked = {
            Toast.makeText(
                context,
                "Not supported",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}
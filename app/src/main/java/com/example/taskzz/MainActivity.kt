package com.example.taskzz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskzz.login.domain.usecase.DemoCredentialsLoginUseCase
import com.example.taskzz.login.ui.LoginScreen
import com.example.taskzz.login.ui.LoginViewModel
import com.example.taskzz.ui.theme.TaskzzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel

    private val LoginViewModelFactory = object: ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val useCase = DemoCredentialsLoginUseCase()

            return LoginViewModel(useCase) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory)[LoginViewModel::class.java]

        setContent {
            TaskzzTheme {
                // A surface container using the 'background' color from the theme
                val viewModel = hiltViewModel<LoginViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        onLoginCompleted = {
                            Log.d("Main Activity", "log has completed")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskzzTheme {
        Greeting("Android")
    }
}
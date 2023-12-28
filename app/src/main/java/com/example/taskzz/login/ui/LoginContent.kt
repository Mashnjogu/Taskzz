package com.example.taskzz.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskzz.R
import com.example.taskzz.core.VerticalSpacer
import com.example.taskzz.ui.components.PrimaryButton
import com.example.taskzz.ui.components.SecondaryButton
import com.example.taskzz.ui.components.TaskzTextField
import com.example.taskzz.ui.theme.TaskzzTheme

@Composable
fun LoginContent(
    loginViewState: LoginViewState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit
){
    Scaffold(
        Modifier.background(MaterialTheme.colorScheme.primary)
    ){ it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(dimensionResource(id = R.dimen.screen_padding))
        ){
            Spacer(modifier = Modifier.weight(1F))

            UserNameInput(text = loginViewState.email, onTextChanged = onUserNameChanged)

            VerticalSpacer(height = 12.dp)

            PasswordInput(text = loginViewState.password, onTextChanged = onPasswordChanged)

            VerticalSpacer(height = 48.dp)

            LoginButton(onClick = onLoginClicked)

            VerticalSpacer(height = 12.dp)

            SignUpButton(onClick = onSignUpClicked)
        }
    }

}

@Composable
private fun SignUpButton(onClick: () -> Unit) {
    SecondaryButton(
        text = "Sign Up",
        onClick = onClick
    )
}

@Composable
private fun LoginButton(onClick: () -> Unit) {
    PrimaryButton(
        text = "Log In",
        onClick = onClick
    )
}

@Composable
private fun PasswordInput(
    text: String,
    onTextChanged: (String) -> Unit
) {
    TaskzTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Password"
    )
}

@Composable
private fun UserNameInput(
    text: String,
    onTextChanged: (String) -> Unit
) {
    TaskzTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Email"
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun EmptyLoginContentPreview(){
    val viewState = LoginViewState(email = "", password = "")
    TaskzzTheme {
        LoginContent(
            loginViewState = viewState,
            {},
            {},
            {},
            {}
        )
    }
}
package com.example.taskzz.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.taskzz.R
import com.example.taskzz.core.VerticalSpacer
import com.example.taskzz.login.domain.model.Credentials
import com.example.taskzz.login.domain.model.Email
import com.example.taskzz.login.domain.model.Password
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
        Box(modifier = Modifier.padding(it)){
            //error could come up within paddingValues
            LogoInputsColumn(
                it,
                loginViewState,
                onUserNameChanged,
                onPasswordChanged,
                onLoginClicked,
                onSignUpClicked
            )

            CircularProgressIndicator(
                modifier = Modifier.wrapContentSize()
                    .align(Alignment.Center)
            )
        }
    }

}

@Composable
private fun LogoInputsColumn(
    it: PaddingValues,
    loginViewState: LoginViewState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(dimensionResource(id = R.dimen.screen_padding))
    ) {
        Spacer(modifier = Modifier.weight(1F))

        Text(text = "app icon")
        Spacer(modifier = Modifier.height(12.dp))

        EmailInput(
            text = loginViewState.credentials.email.value,
            onTextChanged = onUserNameChanged,
            /*
                if error is of type InputError, we can get the emailInputErrorMessage
                 */
            errorMessage = (loginViewState as? LoginViewState.InputError)?.emailInputErrorMessage
        )

        VerticalSpacer(height = 12.dp)

        PasswordInput(
            text = loginViewState.credentials.password.value,
            onTextChanged = onPasswordChanged,
            errorMessage = (loginViewState as? LoginViewState.InputError)?.passwordInputErrorMessage
        )

        if (loginViewState is LoginViewState.SubmissionError) {
            Text(
                text = loginViewState.errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        VerticalSpacer(height = 48.dp)

        LoginButton(
            onClick = onLoginClicked,
            enabled = loginViewState.buttonEnabled
        )

        VerticalSpacer(height = 12.dp)

        SignUpButton(
            onClick = onSignUpClicked,
            enabled = loginViewState.buttonEnabled
        )
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    SecondaryButton(
        text = "Sign Up",
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    PrimaryButton(
        text = "Log In",
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun PasswordInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?
) {
    TaskzTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Password",
        errorMessage = errorMessage,
        visualTransformation = PasswordVisualTransformation(mask = '*')
    )
}

@Composable
private fun EmailInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?
) {
    TaskzTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = "Email",
        errorMessage = errorMessage
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
private fun LoginContentPreview(
    @PreviewParameter(LoginViewStateProvider::class)
    loginViewState: LoginViewState
){
    val viewState = LoginViewState.Initial
    TaskzzTheme {
        LoginContent(
            loginViewState = loginViewState,
            {},
            {},
            {},
            {}
        )
    }
}

class LoginViewStateProvider: PreviewParameterProvider<LoginViewState>{
    override val values: Sequence<LoginViewState>
        get() {
            val activeCredentials = Credentials(
                email = Email("test3@gmail.com"),
                password = Password("rtry5#i")
            )
            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(activeCredentials),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmissionError(
                    activeCredentials,
                    "Something went wrong"
                ),
                LoginViewState.InputError(
                    credentials = activeCredentials,
                    emailInputErrorMessage = "Please enter an email",
                    passwordInputErrorMessage = "Please enter a password",
                )
            )
        }


}


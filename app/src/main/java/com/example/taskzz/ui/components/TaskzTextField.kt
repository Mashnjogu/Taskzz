package com.example.taskzz.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskzz.R
import com.example.taskzz.ui.theme.TaskzzTheme
import com.example.taskzz.ui.theme.TextFieldShape


@Composable
fun TaskzTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String?,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholderText: String? = null,
    focusRequester: FocusRequester = FocusRequester(),
) {
    val labelComposable: (@Composable () -> Unit)? = labelText?.let {
        @Composable {
            Text(
                text = labelText,
            )
        }
    }

    val placeholderComposable: (@Composable () -> Unit)? = placeholderText?.let {
        @Composable {
            Text(
                text = placeholderText,
            )
        }
    }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            label = labelComposable,
            shape = TextFieldShape,
            modifier = modifier
                .heightIn(dimensionResource(id = R.dimen.text_field_height))
                .fillMaxWidth()
                .focusRequester(focusRequester),
            isError = (errorMessage != null),
            visualTransformation = visualTransformation,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            placeholder = placeholderComposable,
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp,
                    ),
            )
        }
    }
}

@Preview(
    name = "Night Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun FilledTOATextFieldPreview() {
    TaskzzTheme {
        Surface {
            TaskzTextField(
                text = "TOA text field",
                onTextChanged = {},
                labelText = "Label",
            )
        }
    }
}

@Preview(
    name = "Night Mode - Error",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Error",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun ErrorTOATextFieldPreview() {
    TaskzzTheme {
        Surface {
            TaskzTextField(
                text = "TOA text field",
                onTextChanged = {},
                labelText = "Label",
                errorMessage = "Plz enter this",
            )
        }
    }
}

@Preview(
    name = "Night Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun EmptyTOATextFieldPreview() {
    TaskzzTheme {
        Surface {
            TaskzTextField(
                text = "",
                onTextChanged = {},
                labelText = "Label",
            )
        }
    }
}
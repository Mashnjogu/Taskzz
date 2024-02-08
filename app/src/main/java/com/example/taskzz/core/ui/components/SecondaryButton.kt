package com.example.taskzz.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskzz.R
import com.example.taskzz.ui.theme.TaskzzTheme
import com.example.taskzz.ui.theme.buttonShape

@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textColor: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true
){

    TextButton(
        onClick = onClick,
        shape = buttonShape,
        modifier = modifier.height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        enabled = enabled
    ){
        Text(
            text.toUpperCase(androidx.compose.ui.text.intl.Locale.current),
            color = textColor
        )
    }
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
private fun SecondaryButtonEnabledPreview(){
    TaskzzTheme {
        SecondaryButton(
            "Click me",
            onClick = {},
            enabled = true
        )
    }
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
private fun SecondaryButtonDisabledPreview(){
    TaskzzTheme {
        SecondaryButton(
            "Click me",
            onClick = {},
            enabled = false
        )
    }
}


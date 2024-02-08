package com.example.taskzz.core.ui.components

import android.content.res.Configuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskzz.ui.theme.TaskzzTheme


@Composable
fun TaskzTextButton(
    text: String,
    onClick: () -> Unit
){
    TextButton(onClick = onClick) {
        Text(
            text = text.toUpperCase(androidx.compose.ui.text.intl.Locale.current)
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
private fun TaskListPreview(){

    TaskzzTheme {
        TaskzTextButton(
            text = "Text Button",
            onClick = {}
        )
    }
}
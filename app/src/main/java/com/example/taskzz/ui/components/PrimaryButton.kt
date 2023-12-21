package com.example.taskzz.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskzz.R
import com.example.taskzz.ui.theme.TaskzzTheme
import java.util.Locale

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary
){
    val buttonColors = buttonColors(
        containerColor = color
    )
    Button(
        onClick = onClick,
        colors = buttonColors,
        shape = RoundedCornerShape(50),
        modifier = modifier.height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth()
    ){
        Text(text.toUpperCase(androidx.compose.ui.text.intl.Locale.current))
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
private fun PrimaryButtonPreview(){
    TaskzzTheme {
        PrimaryButton(
            "Click me",
            onClick = {}
        )
    }
}


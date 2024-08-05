package com.example.taskzz.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskzz.ui.theme.TaskzzTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TaskzzDatePicker(
    value: LocalDate,
    onValueChanged: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.onSurface,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    errorMessage: String? = null
) {

    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        backgroundColor = MaterialTheme.colorScheme.surface,
        buttons = {
            positiveButton(
                text = "Ok",
            )
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = value,
            onDateChange = onValueChanged,
            colors = md3PickerMaterialColors()
        )
    }

    val hasError = errorMessage != null

    val borderColorToUse = if (hasError){
        MaterialTheme.colorScheme.error
    }else{
        borderColor
    }

    val iconColorToUse = if (hasError){
        MaterialTheme.colorScheme.error
    }else{
        iconColor
    }


    Column(
        modifier = modifier
    ){

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = borderColorToUse,
                    shape = RoundedCornerShape(
                        50
                    )
                )
                .clickable {
                    dialogState.show()
                }
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = value.toUiString(),
                    modifier = Modifier.weight(1f),
                    color = textColor
                )

                Icon(
                    Icons.Default.DateRange,
                    "Select Date",
                    tint = iconColorToUse
                )
            }
        }

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

private fun LocalDate.toUiString(): String{
    val formatter = DateTimeFormatter.ofPattern("MMMM/dd/yyyy")
    return formatter.format(this)
}
@Composable
private fun md3PickerMaterialColors(
    headerBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    headerTextColor: Color = MaterialTheme.colorScheme.onPrimary,
    calendarHeaderTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    dateActiveBackgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    dateInactiveBackgroundColor: Color = Color.Transparent,
    dateActiveTextColor: Color = MaterialTheme.colorScheme.primary,
    dateInactiveTextColor: Color = MaterialTheme.colorScheme.onBackground
): com.vanpra.composematerialdialogs.datetime.date.DatePickerColors{
    return com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults.colors(
        headerBackgroundColor = headerBackgroundColor,
        headerTextColor = headerTextColor,
        calendarHeaderTextColor = calendarHeaderTextColor,
        dateActiveBackgroundColor = dateActiveBackgroundColor,
        dateInactiveBackgroundColor = dateInactiveBackgroundColor,
        dateActiveTextColor = dateActiveTextColor,
        dateInactiveTextColor = dateInactiveTextColor
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
private fun TaskzzDatePickerPreview(){
    TaskzzTheme {
        Surface {
            TaskzzDatePicker(
                value = LocalDate.now(),
                onValueChanged = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
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
private fun TaskzzDatePickerPreviewWithErrorMessage(){
    TaskzzTheme {
        Surface {
            TaskzzDatePicker(
                value = LocalDate.now().minusDays(1),
                onValueChanged = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                errorMessage = "Scheduled Date cannot be in the past"
            )
        }
    }
}
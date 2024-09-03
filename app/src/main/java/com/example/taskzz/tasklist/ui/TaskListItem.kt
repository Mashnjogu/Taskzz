package com.example.taskzz.tasklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskzz.ui.theme.TaskzzTheme
import com.example.taskzz.R
import com.example.taskzz.core.ui.components.TaskzTextButton
import com.example.taskzz.tasklist.domain.model.Task
import java.time.LocalDate

@Composable
fun TaskListItem(
    task: Task,
    onRescheduleClicked: () -> Unit,
    onDoneClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier){
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.End
        ){
            TaskText(
                task.description
            )

            ButtonRow(
                onRescheduleClicked = onRescheduleClicked,
                onDoneClicked = onDoneClicked
            )
        }
    }
}

@Composable
private fun ButtonRow(
    onRescheduleClicked: () -> Unit,
    onDoneClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RescheduleButton(onRescheduleClicked)

        DoneButton(onDoneClicked)
    }
}

@Composable
private fun RescheduleButton(
    onClick:() -> Unit
) {

    TaskzTextButton(
        text = stringResource(id = R.string.reschedule),
        onClick = onClick
    )

}

@Composable
private fun DoneButton(
    onClick:() -> Unit
) {
    TaskzTextButton(
        text = stringResource(id = R.string.done),
        onClick = onClick
    )
}

@Composable
private fun TaskText(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
private fun TaskListPreview(){

    TaskzzTheme {
        val task = Task(
            id = "test",
            description = "Clean my office space.",
            scheduledDate = LocalDate.now(),
        )

        TaskListItem(
            task = task,
            onRescheduleClicked = {},
            onDoneClicked = {}
        )
    }
}
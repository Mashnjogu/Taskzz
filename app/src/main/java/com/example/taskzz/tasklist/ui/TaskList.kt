package com.example.taskzz.tasklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.ui.theme.TaskzzTheme
import com.example.taskzz.R
import java.time.LocalDate

@Composable
fun TaskList(
    incompleteTasks: List<Task>,
    completedTasks: List<Task>,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_padding)),
        modifier = modifier
    ){
        item {
            SectionHeader(text = stringResource(id = R.string.incomplete_tasks_header))
        }

        items(incompleteTasks){task ->
            TaskListItem(
                task = task,
                onRescheduleClicked = {
                    onRescheduleClicked(task)
                },
                onDoneClicked = {
                    onDoneClicked(task)
                }
            )
        }

        item {
            SectionHeader(text = stringResource(id = R.string.completed_tasks_header))
        }

        items(completedTasks){task ->
            TaskListItem(
                task = task,
                onRescheduleClicked = {
                    onRescheduleClicked(task)
                },
                onDoneClicked = {
                    onDoneClicked(task)
                }
            )
        }
    }
}

@Composable
private fun SectionHeader(
    text: String
){
    Text(text = text,
//        style = MaterialTheme.typography.headlineSmall
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
    val incompleteTasks = (1..10).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDate = LocalDate.now(),
            completed = false
        )
    }

    val completedTasks = (1..10).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDate = LocalDate.now(),
            completed = true
        )
    }

    TaskzzTheme {
        TaskList(
            incompleteTasks = emptyList(),
            completedTasks = completedTasks,
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}
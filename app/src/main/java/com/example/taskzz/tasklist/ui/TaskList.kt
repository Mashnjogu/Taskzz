package com.example.taskzz.tasklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskzz.ui.theme.TaskzzTheme
import com.example.taskzz.R
import com.example.taskzz.core.models.Task
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
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
        if (incompleteTasks.isEmpty()){
            item {
                EmptySectionCard(text = stringResource(R.string.no_incomplete_task_label))
            }
        }else{
            items(
                incompleteTasks,
                key = {it ->
                    it.id
                }
            ){task ->
                TaskListItem(
                    task = task,
                    onRescheduleClicked = {
                        onRescheduleClicked(task)
                    },
                    onDoneClicked = {
                        onDoneClicked(task)
                    },
                    modifier = Modifier.testTag("INCOMPLETE_TASK: ${task.id}")
                        .animateItemPlacement()
                )
            }
        }


        item {
            SectionHeader(text = stringResource(id = R.string.completed_tasks_header))
        }

        if(completedTasks.isEmpty()){
            item{
                EmptySectionCard(text = stringResource(id = R.string.no_completed_tasks_label))
            }
        }else{
            items(
                completedTasks,
                key = {it ->
                    it.id
                }
            ){task ->
                TaskListItem(
                    task = task,
                    onRescheduleClicked = {
                        onRescheduleClicked(task)
                    },
                    onDoneClicked = {
                        onDoneClicked(task)
                    },
                    modifier = Modifier.testTag("COMPLETE_TASK: ${task.id}")
                        .animateItemPlacement()
                )
            }
        }


    }


}

@Composable
private fun EmptySectionCard(text: String, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
    ){
        Text(
            text = text,
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 24.dp
            )
        )
    }
}

@Composable
private fun SectionHeader(
    text: String
){
    Text(text = text,
//        style = MaterialTheme.typography.headlineMedium
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
            scheduledDateMillis = 0L,
            completed = false
        )
    }

    val completedTasks = (1..10).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDateMillis = 0L,
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

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun NoIncompleteTasksListPreview() {
    val completedTasks = (1..3).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            completed = true,
            scheduledDateMillis = 0L
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

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun NoCompletedTasksListPreview() {
    val incompleteTasks = (1..3).map { index ->
        Task(
            id = "$index",
            description = "Test task: $index",
            scheduledDateMillis = 0L,
            completed = false,
        )
    }

    TaskzzTheme {
        TaskList(
            incompleteTasks = incompleteTasks,
            completedTasks = emptyList(),
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun NoTasksListPreview() {
    TaskzzTheme {
        TaskList(
            incompleteTasks = emptyList(),
            completedTasks = emptyList(),
            onRescheduleClicked = {},
            onDoneClicked = {},
        )
    }
}


package com.example.taskzz.home.tasklist.ui


import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.taskzz.home.tasklist.domain.model.Task
import com.example.taskzz.R
import com.example.taskzz.ui.theme.TaskzzTheme


@Composable
fun TaskListContent(
    viewState: TaskListViewState,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    onAddButtonClicked: () -> Unit
){
    if (viewState is TaskListViewState.Loaded){
        LoadedTasksContent(
            viewState,
            onAddButtonClicked,
            onRescheduleClicked,
            onDoneClicked
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun LoadedTasksContent(
    viewState: TaskListViewState.Loaded,
    onAddButtonClicked: () -> Unit,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit
) {
    Scaffold(
        topBar = {
            TaskListToolBar()
        },
        floatingActionButton = {
                AddTaskButton(
                    onClicked = onAddButtonClicked
                )

        }
    ) { paddingValues ->
            TaskList(
                tasks = viewState.tasks,
                onRescheduledClicked = onRescheduleClicked,
                onDoneClicked = onDoneClicked,
                modifier = Modifier.padding(paddingValues)
            )

    }
}

@Composable
private fun TaskListToolBar() {
    val toolBarHeight = 84.dp
    Surface(
        color = MaterialTheme.colorScheme.primary
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(toolBarHeight)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    "Previous Day",
                    modifier = Modifier.size(84.dp)
                )
            }
            Text(
                text = "Today",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
//                fontSize = 36.sp,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    "Previous Day",
                    modifier = Modifier.size(84.dp)
                )
            }

        }
    }
}

@Composable
private fun AddTaskButton(
    onClicked: () -> Unit
) {
    FloatingActionButton(onClick = onClicked) {
        Icon(
            Icons.Default.Add,
            stringResource(id = R.string.add_task)
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
private fun TaskListContentPreview() {

    val tasks = (1..10).map { index ->
        Task(
            description = "Task $index",
        )
    }

    val viewState = TaskListViewState.Loaded(tasks)

    TaskzzTheme {
        TaskListContent(
            viewState = viewState,
            onRescheduleClicked = {},
            onDoneClicked = {},
            onAddButtonClicked = {}
        )
    }
}

package com.example.taskzz.tasklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.ui.theme.TaskzzTheme
import com.example.taskzz.R
@Composable
fun TaskList(
    tasks: List<TaskDisplayModel>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.list_padding)),
        modifier = modifier
    ){
        items(tasks){task ->
            TaskListItem(
                task = task,
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
private fun TaskListPreview(){
    val tasks = (1..10).map { index ->
        TaskDisplayModel(
            description = "Task $index",
            scheduledDate = "Today",
            onRescheduleClicked = {},
            onDoneClicked = {}
        )
    }
    TaskzzTheme {
        TaskList(
            tasks = tasks,
        )
    }
}
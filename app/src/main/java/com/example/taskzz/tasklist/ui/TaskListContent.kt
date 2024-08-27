package com.example.taskzz.tasklist.ui


import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.R
import com.example.taskzz.core.ui.components.MaterialCircularProgressIndicator
import com.example.taskzz.core.ui.components.getString
import com.example.taskzz.ui.theme.TaskzzTheme
import java.time.LocalDate


@Composable
fun TaskListContent(
    viewState: TaskListViewState,
    onRescheduleClicked: (Task) -> Unit,
    onDoneClicked: (Task) -> Unit,
    onAddButtonClicked: () -> Unit,
    onPreviousDateButtonClicked: () -> Unit,
    onNextDateButtonClicked: () -> Unit
){

    Scaffold(
        topBar = {
            TaskListToolBar(
                onLeftButtonClicked = onPreviousDateButtonClicked,
                onRightButtonClicked = onNextDateButtonClicked,
                title = viewState.selectedDateString.getString()
            )
        },
        floatingActionButton = {
            AddTaskButton(
                onClicked = onAddButtonClicked
            )

        }
    ) { paddingValues ->
        if(!viewState.showLoading){
            TaskList(
                incompleteTasks = viewState.incompleteTasks.orEmpty(),
                completedTasks = viewState.completedTasks.orEmpty(),
                onRescheduleClicked = onRescheduleClicked,
                onDoneClicked = onDoneClicked,
                modifier = Modifier
                    .padding(paddingValues),
            )
        }

        if(viewState.showLoading){

            Box(modifier = Modifier.fillMaxSize()){

                Text(text = "Loading.....", modifier = Modifier.align(Alignment.Center))
            }

        }

    }

}

//progress indicator is not at the right place

@Composable
private fun TaskListToolBar(
    onLeftButtonClicked: () -> Unit,
    onRightButtonClicked: () -> Unit,
    title: String
) {
    val toolBarHeight = 84.dp
    Surface(
        color = MaterialTheme.colorScheme.primary
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .statusBarsPadding()
                .height(toolBarHeight)

        ) {
            IconButton(onClick = onLeftButtonClicked) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    "View Previous Date's Task",
                    modifier = Modifier.size(84.dp)
                )
            }
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
//                fontSize = 36.sp,
//                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onRightButtonClicked) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    stringResource(R.string.view_next_date_content_description),
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
            id = "$index",
            description = "Test task: $index",
            scheduledDate = LocalDate.now(),
        )
    }

    val viewState = TaskListViewState(
        showLoading = false,
        incompleteTasks = tasks,
    )

    TaskzzTheme {
        TaskListContent(
            viewState = viewState,
            onRescheduleClicked = {},
            onDoneClicked = {},
            onAddButtonClicked = {},
            onPreviousDateButtonClicked = {},
            onNextDateButtonClicked = {}
        )
    }
}

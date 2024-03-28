package com.example.taskzz.tasklist.ui

import android.util.Log
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskzz.addtask.ui.AddTasksScreen
import com.example.taskzz.destinations.AddTasksScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
){
    val viewState = viewModel.viewState.collectAsState()

    println("TaskListScreen viewstate is ${viewState.value}")
        TaskListContent(
            viewState = viewState.value,
            onRescheduleClicked = {},
            onDoneClicked = {},
            onAddButtonClicked = {
                navigator.navigate(AddTasksScreenDestination)
            }
        )

}
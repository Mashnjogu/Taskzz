package com.example.taskzz.tasklist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
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
        },
        onPreviousDateButtonClicked = {
            viewModel.onPreviousDateButtonClicked()
        },
        onNextDateButtonClicked = {
            viewModel.onNextDayButtonClicked()
        }
    )
}


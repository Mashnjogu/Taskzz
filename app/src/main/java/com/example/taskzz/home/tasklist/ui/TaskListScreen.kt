package com.example.taskzz.home.tasklist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination(

)
@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = hiltViewModel()
){
    val viewState = viewModel.viewState.collectAsState()

    println("The viewstate from the viewmodel is ${viewState.value}")


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){

        Text(text = "The state is ${viewState.value}")
        Spacer(modifier = Modifier.height(8.dp))
    }

    TaskListContent(
        viewState = viewState.value,
        onRescheduleClicked = {},
        onDoneClicked = {},
        onAddButtonClicked = {}
    )

}
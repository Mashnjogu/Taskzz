package com.example.taskzz.addtask.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskzz.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddTasksScreen(
    navigator: DestinationsNavigator,
    viewModel: AddTasksViewModel = hiltViewModel()
){

    val viewState = viewModel.viewState.collectAsState()
    
        DisposableEffect(viewState.value){
        if (viewState.value is AddTaskViewState.Completed){
            navigator.popBackStack()
        }
            onDispose {  }
    }

    Surface{
        AddTaskContent(
            viewState = viewState.value,
            onTaskDescriptionChanged = viewModel::onTaskDescriptionChanged,
            onTaskScheduledDateChanged = viewModel::onTaskScheduledDateChanged,
            onSubmitClicked = viewModel::onSubmitButtonClicked,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_padding))
                .statusBarsPadding()
        )
    }

}
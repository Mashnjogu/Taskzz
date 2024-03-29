package com.example.taskzz.addtask.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskzz.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AddTasksScreen(
    viewModel: AddTasksViewModel = hiltViewModel()
){
    val viewState = viewModel.viewState.collectAsState()

    Surface{
        AddTaskContent(
            viewState = viewState.value,
            onTaskDescriptionChanged = {},
            onTaskScheduledDateChanged = {},
            onSubmitClicked = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_padding))
                .statusBarsPadding()
        )
    }

}
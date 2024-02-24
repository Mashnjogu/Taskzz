package com.example.taskzz.home.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.home.tasklist.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
): ViewModel(){

    private val _viewState: MutableStateFlow<TaskListViewState> = MutableStateFlow(TaskListViewState.Loading)
    val viewState: StateFlow<TaskListViewState> = _viewState

    private var viewModelInitialized = false

    init {

        viewModelScope.launch(Dispatchers.IO){

            val getTasksResults = getAllTasksUseCase()

            _viewState.value = when(getTasksResults){
                is Result.Success -> {
                    TaskListViewState.Loaded(
                        tasks = getTasksResults.data
                    )
                }
                is Result.Error -> {
                    TaskListViewState.Error(
                        errorMessage = UiText.StringText("Something went wrong")
                    )
                }
            }
        }

    }
}
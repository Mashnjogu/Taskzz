package com.example.taskzz.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.usecase.GetAllTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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


    init {

        viewModelScope.launch(Dispatchers.Default){

            val getTasksResults = getAllTasksUseCase()

            _viewState.value = when(getTasksResults){
                is Result.Success -> {
                    val displayModel = getTasksResults.data.map {
                        it.toDisplayModel()
                    }

                    TaskListViewState.Loaded(
                        tasks = displayModel
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
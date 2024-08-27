package com.example.taskzz.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.usecase.GetTasksForDateUseCase
import com.example.taskzz.tasklist.domain.usecase.MarkTaskAsCompleteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getTasksForDateUseCase: GetTasksForDateUseCase,
    private val markTaskAsCompleteUseCase: MarkTaskAsCompleteUseCase

): ViewModel(){

    private val _viewState: MutableStateFlow<TaskListViewState> = MutableStateFlow(TaskListViewState())
    val viewState = _viewState.asStateFlow()

    init {

        _viewState.map {viewState ->
            viewState.selectedDate
        }.distinctUntilChanged() //return a viewstate only when the selected date changes
            .flatMapLatest { selectedDate ->
                clearTasksAndShowLoading()

                getTasksForDateUseCase.invoke(date = selectedDate)
            }.onEach {result ->
                _viewState.value = getViewStateForTaskListResult(result = result)
            }
            .launchIn(viewModelScope)
    }

    private fun clearTasksAndShowLoading() {
        _viewState.value = _viewState.value.copy(
            showLoading = true,
            incompleteTasks = null,
            completedTasks = null
        )
    }

    private fun getViewStateForTaskListResult(
        result: Result<List<Task>>
    ): TaskListViewState{
        return when(result){
            is Result.Success -> {

                val (completedTasks,incompleteTasks)  = result.data.partition {task ->
                    task.completed
                }
                _viewState.value.copy(
                    incompleteTasks = incompleteTasks,
                    completedTasks = completedTasks,
                    showLoading = false
                )
            }

            is Result.Error -> {
                _viewState.value.copy(
                    errorMessage = UiText.StringText("Something went wrong."),
                    showLoading = false
                )
            }
        }
    }
    fun onPreviousDateButtonClicked(){
        _viewState.value = viewState.value.copy(
            selectedDate = _viewState.value.selectedDate.minusDays(1)
        )
    }

    fun onNextDayButtonClicked(){
        _viewState.value = viewState.value.copy(
            selectedDate = _viewState.value.selectedDate.plusDays(1)
        )
    }

    fun onDoneButtonClicked(task: Task) {
        viewModelScope.launch {
            markTaskAsCompleteUseCase.invoke(task)
        }
    }

}
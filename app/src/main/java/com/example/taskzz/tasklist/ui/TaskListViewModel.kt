package com.example.taskzz.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.usecase.GetAllTasksUseCase
import com.example.taskzz.tasklist.domain.usecase.RescheduleTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
//    private val rescheduleTaskUseCase: RescheduleTaskUseCase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
): ViewModel(){

    private val _viewState: MutableStateFlow<TaskListViewState> = MutableStateFlow(TaskListViewState.Loading)
    val viewState: StateFlow<TaskListViewState> = _viewState


    init {

        viewModelScope.launch(defaultDispatcher){

            val getTasksResults = getAllTasksUseCase()

            _viewState.value = when(getTasksResults){
                is Result.Success -> {
                    val displayModel = getTasksResults.data.map {task ->
                        mapToDisplayModel(task)
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

    private fun mapToDisplayModel(task: Task): TaskDisplayModel {
        val friendlyDatePattern = "MMM dd yyyy"
        val friendlyDateFormatter = DateTimeFormatter.ofPattern(friendlyDatePattern)

        return TaskDisplayModel(
            description = task.description,
            scheduledDate = friendlyDateFormatter.format(task.scheduledDate),
            onRescheduleClicked = {
                viewModelScope.launch {
//                    rescheduleTaskUseCase(task.id)
                }
            },
            onDoneClicked = {

            }
        )
    }


}
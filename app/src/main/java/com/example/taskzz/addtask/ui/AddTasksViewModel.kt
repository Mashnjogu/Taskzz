package com.example.taskzz.addtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.addtask.domain.usecase.AddTasksUseCase
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
@HiltViewModel
class AddTasksViewModel @Inject constructor(
    private val addTasksUseCase: AddTasksUseCase
) : ViewModel(){

    private val _viewState: MutableStateFlow<AddTaskViewState> = MutableStateFlow(AddTaskViewState.Initial)
    val viewState: StateFlow<AddTaskViewState> = _viewState

    fun onTaskScheduledDateChanged(newDate: LocalDate){
        val currentValue = _viewState.value.taskInput
        val newValue = currentValue.copy(scheduledDate = newDate)

        _viewState.value = AddTaskViewState.Active(taskInput = newValue)
    }

    fun onTaskDescriptionChanged(newDescription: String){
        val currentDescription = _viewState.value.taskInput
        val newDescriptionValue = currentDescription.copy(description = newDescription)

        _viewState.value = AddTaskViewState.Active(taskInput = newDescriptionValue)
    }

    fun onSubmitButtonClicked(){
        val taskToCreate = Task(
            description = _viewState.value.taskInput.description,
        )

        viewModelScope.launch {
            val result = addTasksUseCase(taskToCreate)

            _viewState.value = when(result){
                is Result.Success -> {
                    AddTaskViewState.Completed
                }
                is Result.Error -> {
                    AddTaskViewState.SubmisssionError(
                        taskInput = _viewState.value.taskInput,
                        errorMessage = UiText.StringText("Unable to add Task")
                    )
                }
            }
        }


    }


}
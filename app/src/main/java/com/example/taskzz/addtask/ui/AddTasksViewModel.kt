package com.example.taskzz.addtask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskzz.R
import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.addtask.domain.model.TaskInput
import com.example.taskzz.addtask.domain.usecase.AddTasksUseCase
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
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

        _viewState.value = AddTaskViewState.Active(
            taskInput = newValue,
            descriptionInputErrorMessage = (_viewState.value as? AddTaskViewState.Active)
                ?.descriptionInputErrorMessage,
            scheduledDateErrorMessage = null
        )
    }

    fun onTaskDescriptionChanged(newDescription: String){
        val currentDescription = _viewState.value.taskInput
        val newDescriptionValue = currentDescription.copy(description = newDescription)

        _viewState.value = AddTaskViewState.Active(
            taskInput = newDescriptionValue,
            descriptionInputErrorMessage = null,
            scheduledDateErrorMessage = (_viewState.value as? AddTaskViewState.Active)
                ?.scheduledDateErrorMessage
        )
    }

    fun onSubmitButtonClicked(){
        val taskToCreate = Task(
            id = UUID.randomUUID().toString(),
            description = _viewState.value.taskInput.description,
            scheduledDate = _viewState.value.taskInput.scheduledDate,
            completed = false
        )

        viewModelScope.launch {
            val result = addTasksUseCase.invoke(taskToCreate)

            _viewState.value = when(result){
                is AddTaskResult.Success -> {
                    AddTaskViewState.Completed
                }
                is AddTaskResult.Failure.Unkown -> {
                    AddTaskViewState.SubmissionError(
                        taskInput = _viewState.value.taskInput,
                        errorMessage = UiText.StringText("Unable to add Task")
                    )
                }
                is AddTaskResult.Failure.InvalidInput -> {
                    result.toViewState(taskInput = _viewState.value.taskInput)
                }
            }
        }


    }


}

private fun AddTaskResult.Failure.InvalidInput.toViewState(taskInput: TaskInput): AddTaskViewState{
    return AddTaskViewState.Active(
        taskInput = taskInput,
        descriptionInputErrorMessage = UiText.ResourceText(R.string.empty_task_description).takeIf {
            this.emptyDescription
        },
        scheduledDateErrorMessage = UiText.ResourceText(R.string.invalid_scheduled_date).takeIf {
            this.scheduledDateInPast
        },
    )
}


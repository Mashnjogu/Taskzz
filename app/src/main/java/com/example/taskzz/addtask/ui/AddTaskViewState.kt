package com.example.taskzz.addtask.ui

import com.example.taskzz.addtask.domain.model.TaskInput
import com.example.taskzz.core.ui.components.UiText

sealed class AddTaskViewState (
    open val taskInput: TaskInput = TaskInput(),
    val inputEnabled: Boolean = true
){

    object Initial : AddTaskViewState(
        taskInput = TaskInput()
    )
    data class Active(
        override val taskInput: TaskInput,
        val descriptionInputErrorMessage: UiText? = null,
        val scheduledDateErrorMessage: UiText? = null
    ): AddTaskViewState(
        taskInput = taskInput,
    )

    data class Submitting(
        override val taskInput: TaskInput,
    ):AddTaskViewState(
        taskInput = taskInput,
        inputEnabled = false
    )

    data class SubmissionError(
        override val taskInput: TaskInput,
        val errorMessage: UiText
    ): AddTaskViewState(
        taskInput = taskInput,
    )

    object Completed: AddTaskViewState()
}
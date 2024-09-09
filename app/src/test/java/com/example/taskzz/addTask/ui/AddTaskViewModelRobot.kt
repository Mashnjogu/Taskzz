package com.example.taskzz.addTask.ui

import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.addtask.ui.AddTaskViewState
import com.example.taskzz.addtask.ui.AddTasksViewModel
import com.example.taskzz.core.models.Task
import com.example.taskzz.fakes.FakeAddTaskUseCase
import com.example.taskzz.login.ui.LoginViewState

import com.google.common.truth.Truth.assertThat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class AddTaskViewModelRobot {

    private val fakeAddTaskUseCase = FakeAddTaskUseCase()
    private lateinit var viewModel: AddTasksViewModel

    fun buildViewModel() = apply{
        viewModel = AddTasksViewModel(
            addTasksUseCase = fakeAddTaskUseCase.mock
        )
    }

    fun mockResultForTask(
        task: Task,
        result: AddTaskResult
    )= apply {
        fakeAddTaskUseCase.mockResultForTask(task = task, result = result)
    }

    fun enterDescription(newDescription: String) = apply{
        viewModel.onTaskDescriptionChanged(newDescription)
    }

    fun selectDate(newScheduledDate: Long) = apply{
        val scheduledDate = Instant.ofEpochMilli(newScheduledDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        viewModel.onTaskScheduledDateChanged(scheduledDate)
    }

    fun clickSubmit() = apply{
        viewModel.onSubmitButtonClicked()
    }

    fun assertViewState(expectedViewState: AddTaskViewState)= apply {
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }


}
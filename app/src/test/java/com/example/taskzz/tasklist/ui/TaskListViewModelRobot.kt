package com.example.taskzz.tasklist.ui

import com.example.taskzz.core.data.Result
import com.example.taskzz.fakes.FakeGetAllTasksUseCase
import com.example.taskzz.fakes.FakeTaskListRepository
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.usecase.ProdGetAllTasksUseCase
import com.example.taskzz.tasklist.ui.TaskListViewModel
import com.example.taskzz.tasklist.ui.TaskListViewState
import com.google.common.truth.Truth.assertThat

class TaskListViewModelRobot {

    private val fakeTaskListRepository = FakeTaskListRepository()
    private val fakeGetAllTasksUseCase = FakeGetAllTasksUseCase()
    private lateinit var viewModel: TaskListViewModel

    fun buildViewModel() = apply{
        viewModel = TaskListViewModel(
            getAllTasksUseCase = ProdGetAllTasksUseCase(
                taskListRepository = fakeTaskListRepository.mock
            )
        )
    }

    fun mockAllTestResult(result: Result<List<Task>>) = apply{
        fakeTaskListRepository.mockFetchAllTasks(result)
    }

    fun assertViewState(expectedViewState: TaskListViewState) = apply{
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }


}
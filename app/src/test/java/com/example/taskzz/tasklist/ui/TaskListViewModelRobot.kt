package com.example.taskzz.tasklist.ui

import com.example.taskzz.core.data.Result
import com.example.taskzz.fakes.FakeTaskListRepository
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.usecase.ProdGetIncompleteTasksForDateUseCase
import com.google.common.truth.Truth.assertThat
import java.time.LocalDate

class TaskListViewModelRobot {

    private val fakeTaskListRepository = FakeTaskListRepository()
    private lateinit var viewModel: TaskListViewModel

    fun buildViewModel() = apply{
        viewModel = TaskListViewModel(

            getTasksForDateUseCase = ProdGetIncompleteTasksForDateUseCase(
                taskListRepository = fakeTaskListRepository.mock
            )
        )
    }

    fun mockTestForDateResult(date: LocalDate, result: Result<List<Task>>) = apply{
        fakeTaskListRepository.mockTasksForDateResults(date, result)
    }

    fun assertViewState(expectedViewState: TaskListViewState) = apply{
        val actualViewState = viewModel.viewState.value
        assertThat(actualViewState).isEqualTo(expectedViewState)
    }

    fun clickPreviousDateButton() = apply{
        viewModel.onPreviousDateButtonClicked()
    }

    fun clickNextDateButton() = apply{
        viewModel.onNextDayButtonClicked()
    }


}
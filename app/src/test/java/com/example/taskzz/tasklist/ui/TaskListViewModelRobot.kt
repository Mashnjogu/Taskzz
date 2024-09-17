package com.example.taskzz.tasklist.ui

import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api_test.FakeTaskRepository
import com.example.taskzz.tasklist.domain.usecase.ProdGetTasksForDateUseCase
import com.example.taskzz.tasklist.domain.usecase.ProdMarkTaskAsCompleteUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.ZoneId

class TaskListViewModelRobot {

    private val fakeTaskListRepository = FakeTaskRepository()
    private lateinit var viewModel: TaskListViewModel

    fun buildViewModel() = apply{
        viewModel = TaskListViewModel(

            getTasksForDateUseCase = ProdGetTasksForDateUseCase(
                taskListRepository = fakeTaskListRepository
            ),
            markTaskAsCompleteUseCase = ProdMarkTaskAsCompleteUseCase(
                taskListRepository = fakeTaskListRepository
            )
        )
    }

    fun mockTestForDateResult(date: LocalDate, result: Result<List<Task>>, completed: Boolean) = apply{
        val dateMillis = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val input = Pair(dateMillis, completed)
        fakeTaskListRepository.tasksForDateResults[input] = flowOf(result)
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
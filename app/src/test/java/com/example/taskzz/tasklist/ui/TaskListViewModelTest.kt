package com.example.taskzz.tasklist.ui

import com.example.taskzz.CoroutineTestRule
import com.example.taskzz.tasklist.domain.model.Task
import org.junit.Rule
import org.junit.Test
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import java.time.LocalDate

class TaskListViewModelTest {

    private val testRobot = TaskListViewModelRobot()

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Test
    fun successfulLoad(){
        val task = Task(
            description = "Test task"
        )

        val taskList = listOf(task)

        val taskResponse = Result.Success(
            taskList
        )

        val expectedTaskList = listOf(task)

        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = taskResponse
            )
            .buildViewModel()
            .assertViewState(
                expectedViewState = TaskListViewState(
                    tasks = expectedTaskList,
                    showLoading = false
                )
            )


//        testRobot.mockAllTestResult(taskResponse).buildViewModel()

    }

    @Test
    fun failureLoad(){
        val taskResult: Result<List<Task>> = Result.Error(error = Throwable("Oops an Error Occured"))

        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = taskResult
            )
            .buildViewModel()
            .assertViewState(
                expectedViewState = TaskListViewState(
                    errorMessage = UiText.StringText("Something went wrong"),
                    showLoading = false
                )
            )
    }

    @Test
    fun clickPreviousDate(){
        val task = Task(
            id = "1",
            description = "Test task",
            scheduledDate = LocalDate.now()
        )

        val taskList = listOf(task)

        val taskListResponse = Result.Success(taskList)

        val expectedViewState = TaskListViewState(
            selectedDate = LocalDate.now().minusDays(1),
            tasks = taskList,
            showLoading = false
        )
        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = Result.Success(emptyList())
            )
            .mockTestForDateResult(
                date = LocalDate.now().minusDays(1),
                result = taskListResponse
            )
            .buildViewModel()
            .clickPreviousDateButton()
            .assertViewState(expectedViewState)
    }

    @Test
    fun clickNextDate(){
        val task = Task(
            id = "1",
            description = "Test task",
            scheduledDate = LocalDate.now()
        )

        val taskList = listOf(task)

        val taskListResponse = Result.Success(taskList)

        val expectedViewState = TaskListViewState(
            selectedDate = LocalDate.now().plusDays(1),
            tasks = taskList,
            showLoading = false
        )
        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = Result.Success(emptyList())
            )
            .mockTestForDateResult(
                date = LocalDate.now().plusDays(1),
                result = taskListResponse
            )
            .buildViewModel()
            .clickNextDateButton()
            .assertViewState(expectedViewState)
    }

}



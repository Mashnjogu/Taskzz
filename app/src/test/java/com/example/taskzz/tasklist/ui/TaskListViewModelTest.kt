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
        val incompleteTask = Task(
            id = "Test",
            description = "Test task",
            scheduledDate = LocalDate.now(),
            completed = false,
        )

        val completedTask = incompleteTask.copy(
            completed = true,
        )

        val taskList = listOf(incompleteTask, completedTask)

        val taskResponse = Result.Success(
            taskList
        )

//        val expectedTaskList = listOf(task)

        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = taskResponse,
                completed = true
            )
            .buildViewModel()
            .assertViewState(
                expectedViewState = TaskListViewState(
                    incompleteTasks = listOf(incompleteTask),
                    completedTasks = listOf(completedTask),
                    showLoading = false,
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
                result = taskResult,
                completed = false
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
        val incompleteTask = Task(
            id = "Test",
            description = "Test task",
            scheduledDate = LocalDate.now(),
            completed = false,
        )

        val completedTask = incompleteTask.copy(
            completed = true,
        )

        val taskList = listOf(incompleteTask, completedTask)

        val taskListResponse = Result.Success(taskList)

        val expectedViewState = TaskListViewState(
            selectedDate = LocalDate.now().minusDays(1),
            incompleteTasks = listOf(incompleteTask),
            completedTasks = listOf(completedTask),
            showLoading = false,
        )
        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = Result.Success(emptyList()),
                completed = true
            )
            .mockTestForDateResult(
                date = LocalDate.now().minusDays(1),
                result = taskListResponse,
                completed = true
            )
            .buildViewModel()
            .clickPreviousDateButton()
            .assertViewState(expectedViewState)
    }

    @Test
    fun clickNextDate(){
        val incompleteTask = Task(
            id = "Test",
            description = "Test task",
            scheduledDate = LocalDate.now(),
            completed = false,
        )

        val completedTask = incompleteTask.copy(
            completed = true,
        )

        val taskList = listOf(incompleteTask, completedTask)

        val taskListResponse = Result.Success(taskList)

        val expectedViewState = TaskListViewState(
            selectedDate = LocalDate.now().plusDays(1),
            incompleteTasks = listOf(incompleteTask),
            completedTasks = listOf(completedTask),
            showLoading = false,
        )
        testRobot
            .mockTestForDateResult(
                date = LocalDate.now(),
                result = Result.Success(emptyList()),
                completed = true
            )
            .mockTestForDateResult(
                date = LocalDate.now().plusDays(1),
                result = taskListResponse,
                completed = true
            )
            .buildViewModel()
            .clickNextDateButton()
            .assertViewState(expectedViewState)
    }

}



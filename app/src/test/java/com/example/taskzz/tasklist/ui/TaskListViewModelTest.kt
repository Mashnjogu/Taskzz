package com.example.taskzz.tasklist.ui

import com.example.taskzz.CoroutineTestRule
import com.example.taskzz.home.tasklist.domain.model.Task
import org.junit.Rule
import org.junit.Test
import com.example.taskzz.core.data.Result
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.home.tasklist.ui.TaskListViewState

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

        testRobot
            .mockAllTestResult(taskResponse)
            .buildViewModel()
            .assertViewState(
                expectedViewState = TaskListViewState.Loaded(tasks = taskList)
            )


//        testRobot.mockAllTestResult(taskResponse).buildViewModel()

    }

    @Test
    fun failureLoad(){
        val taskResult: Result<List<Task>> = Result.Error(error = Throwable("Oops an Error Occured"))

        testRobot
            .buildViewModel()
            .assertViewState(
                expectedViewState = TaskListViewState.Error(
                    errorMessage = UiText.StringText("Something went wrong")
                )
            )
    }

}



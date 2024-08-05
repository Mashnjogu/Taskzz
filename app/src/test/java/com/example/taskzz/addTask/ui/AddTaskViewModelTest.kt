package com.example.taskzz.addTask.ui

import com.example.taskzz.CoroutineTestRule
import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.addtask.domain.model.TaskInput
import com.example.taskzz.addtask.ui.AddTaskViewState
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import com.example.taskzz.R

class AddTaskViewModelTest {

    private val testRobot = AddTaskViewModelRobot()

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Test
    fun submitWithEmptyDescription() {
        val taskToSubmit = Task(
            id = "Testing",
            description = "",
            scheduledDate = LocalDate.now()
        )

        val useCaseResult = AddTaskResult.Failure.InvalidInput(
            emptyDescription = true,
            scheduledDateInPast = false
        )

        val expectedViewState = AddTaskViewState.Active(
            taskInput = TaskInput(
                description =  taskToSubmit.description,
                scheduledDate = taskToSubmit.scheduledDate
            ),
            descriptionInputErrorMessage = UiText.ResourceText(R.string.empty_task_description),
            scheduledDateErrorMessage =  null
        )


        testRobot.mockResultForTask(
            task = taskToSubmit,
            result = useCaseResult
        )
            .buildViewModel()
            .enterDescription(taskToSubmit.description)
            .selectDate(taskToSubmit.scheduledDate)
            .clickSubmit()
            .assertViewState(expectedViewState)
    }

    @Test
    fun submitWithInvalidDate() {
        val taskToSubmit = Task(
            id = "Testing",
            description = "Do something",
            scheduledDate = LocalDate.now().minusDays(1)
        )

        val useCaseResult = AddTaskResult.Failure.InvalidInput(
            emptyDescription = false,
            scheduledDateInPast = true
        )

        val expectedViewState = AddTaskViewState.Active(
            taskInput = TaskInput(
                description =  taskToSubmit.description,
                scheduledDate = taskToSubmit.scheduledDate
            ),
            descriptionInputErrorMessage = null,
            scheduledDateErrorMessage =  UiText.ResourceText(R.string.invalid_scheduled_date)
        )


        testRobot.mockResultForTask(
            task = taskToSubmit,
            result = useCaseResult
        )
            .buildViewModel()
            .enterDescription(taskToSubmit.description)
            .selectDate(taskToSubmit.scheduledDate)
            .clickSubmit()
            .assertViewState(expectedViewState)
    }
}
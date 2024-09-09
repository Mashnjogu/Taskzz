package com.example.taskzz.addTask.ui

import com.example.taskzz.CoroutineTestRule
import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.addtask.domain.model.TaskInput
import com.example.taskzz.addtask.ui.AddTaskViewState
import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.core.models.Task

import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import com.example.taskzz.R
import java.time.Instant
import java.time.ZoneId

class AddTaskViewModelTest {

    private val testRobot = AddTaskViewModelRobot()

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @Test
    fun submitWithEmptyDescription() {
        val taskToSubmit = Task(
            id = "Testing",
            description = "",
            scheduledDateMillis = 0L,
            completed = false
        )

        val useCaseResult = AddTaskResult.Failure.InvalidInput(
            emptyDescription = true,
            scheduledDateInPast = false
        )

        val expectedViewState = AddTaskViewState.Active(
            taskInput = TaskInput(
                description =  taskToSubmit.description,
                scheduledDate = Instant.ofEpochMilli(taskToSubmit.scheduledDateMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
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
            .selectDate(taskToSubmit.scheduledDateMillis)
            .clickSubmit()
            .assertViewState(expectedViewState)
    }

    @Test
    fun submitWithInvalidDate() {
        val taskToSubmit = Task(
            id = "Testing",
            description = "Do something",
            scheduledDateMillis =  LocalDate.now().minusDays(1)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
            completed = false
        )

        val useCaseResult = AddTaskResult.Failure.InvalidInput(
            emptyDescription = false,
            scheduledDateInPast = true
        )

        val expectedViewState = AddTaskViewState.Active(
            taskInput = TaskInput(
                description =  taskToSubmit.description,
                scheduledDate = Instant.ofEpochMilli(taskToSubmit.scheduledDateMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
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
            .selectDate(taskToSubmit.scheduledDateMillis)
            .clickSubmit()
            .assertViewState(expectedViewState)
    }
}
package com.example.taskzz.addTask.domain.usecase

import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.addtask.domain.usecase.ProdAddTaskUseCase
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api_test.FakeTaskRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

class ProdAddTaskUseCaseTest {

    private val fakeTaskListRepository = FakeTaskRepository()

    private val useCase = ProdAddTaskUseCase(
        taskListRepository = fakeTaskListRepository
    )

    @Test
    fun submitWithEmptyDescription()= runBlockingTest{
        val taskToSubmit = Task(
            id = "Testing",
            description = "",
            scheduledDateMillis = ZonedDateTime.now().toInstant().toEpochMilli(),
            completed = false,
        )

        val expectedResult = AddTaskResult.Failure.InvalidInput(
            emptyDescription = true,
            scheduledDateInPast = false,
        )

        val actualResult = useCase.invoke(taskToSubmit)
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun submitWithScheduledDateInPast() = runBlockingTest {
        val taskToSubmit = Task(
            id = "Testing",
            description = "Some description",
            scheduledDateMillis = LocalDate.now().minusDays(1)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli(),
            completed = false
        )

        val expectedResult = AddTaskResult.Failure.InvalidInput(
            emptyDescription = false,
            scheduledDateInPast = true,
        )

        val actualResult = useCase.invoke(taskToSubmit)
        assertThat(actualResult).isEqualTo(expectedResult)
    }

}
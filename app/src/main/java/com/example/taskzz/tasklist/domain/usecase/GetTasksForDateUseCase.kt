package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

//tasks for a given date
interface GetTasksForDateUseCase {
    operator fun invoke(
        date: LocalDate,
    ): Flow<TaskListResult>
}
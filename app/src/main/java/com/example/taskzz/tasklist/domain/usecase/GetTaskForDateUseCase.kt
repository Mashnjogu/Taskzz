package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

//tasks for a given date
interface GetTaskForDateUseCase {
    operator fun invoke(date: LocalDate): Flow<TaskListResult>
}
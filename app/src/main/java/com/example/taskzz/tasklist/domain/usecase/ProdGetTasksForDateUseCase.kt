package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import com.example.taskzz.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class ProdGetTasksForDateUseCase  @Inject constructor(
    private val taskListRepository: TaskListRepository
): GetTaskForDateUseCase {
    override fun invoke(date: LocalDate): Flow<TaskListResult> {
        return taskListRepository.fetchTasksForDate(date=date)
    }

}
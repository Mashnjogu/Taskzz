package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api.TaskListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProdGetAllTasksUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): GetAllTasksUseCase {
    override  fun invoke(): Flow<Result<List<Task>>> {
        return taskListRepository.fetchAllTasks()
    }

}
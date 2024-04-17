package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProdGetAllTasksUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): GetAllTasksUseCase {
    override  fun invoke(): Flow<Result<List<Task>>> {
        return taskListRepository.fetchAllTasks()
    }

}
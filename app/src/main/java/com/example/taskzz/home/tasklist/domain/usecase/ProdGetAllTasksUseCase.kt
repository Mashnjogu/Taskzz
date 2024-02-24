package com.example.taskzz.home.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.home.tasklist.domain.model.Task
import com.example.taskzz.home.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class ProdGetAllTasksUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): GetAllTasksUseCase {
    override suspend fun invoke(): Result<List<Task>> {
        return taskListRepository.fetchAllTasks()
    }

}
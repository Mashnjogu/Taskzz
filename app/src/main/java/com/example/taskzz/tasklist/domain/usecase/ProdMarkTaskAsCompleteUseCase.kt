package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class ProdMarkTaskAsCompleteUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): MarkTaskAsCompleteUseCase{
    override suspend fun invoke(task: Task): Result<Unit> {
        val completedTask = task.copy(completed = true)

        return taskListRepository.updateTask(completedTask)
    }

}
package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api.TaskListRepository
import javax.inject.Inject

class ProdMarkTaskAsCompleteUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): MarkTaskAsCompleteUseCase{
    override suspend fun invoke(task: Task): Result<Unit> {
        val completedTask = task.copy(completed = true)

        return taskListRepository.updateTask(completedTask)
    }

}
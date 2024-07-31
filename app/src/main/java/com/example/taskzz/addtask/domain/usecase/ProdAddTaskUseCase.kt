package com.example.taskzz.addtask.domain.usecase

import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import javax.inject.Inject

class ProdAddTaskUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): AddTasksUseCase{
    override suspend fun invoke(task: Task): AddTaskResult {
        val result = taskListRepository.addTask(task)

        return when(result){
            is Result.Success -> AddTaskResult.Success
            is Result.Error -> AddTaskResult.Failure.Unkown
        }
    }


}
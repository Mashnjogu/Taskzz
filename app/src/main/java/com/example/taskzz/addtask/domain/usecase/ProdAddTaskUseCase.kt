package com.example.taskzz.addtask.domain.usecase

import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api.TaskListRepository
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class ProdAddTaskUseCase @Inject constructor(
    private val taskListRepository: TaskListRepository
): AddTasksUseCase{
    override suspend fun invoke(task: Task): AddTaskResult {
        //input validation
        val validationResult = validateInput(task)

        if (validationResult != null){
            return validationResult
        }
        val result = taskListRepository.addTask(task)

        return when(result){
            is Result.Success -> AddTaskResult.Success
            is Result.Error -> AddTaskResult.Failure.Unkown
        }
    }

    private fun validateInput(task: Task): AddTaskResult.Failure.InvalidInput?{
        val emptyDescription = task.description.isEmpty()
        //convert milliseconds to day
        val scheduledDate = Instant.ofEpochMilli(task.scheduledDateMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val scheduledDateInPast = scheduledDate.isBefore(LocalDate.now())

        return if(emptyDescription || scheduledDateInPast){
            AddTaskResult.Failure.InvalidInput(
                emptyDescription = emptyDescription,
                scheduledDateInPast = scheduledDateInPast
            )
        }else{
            null
        }
    }
}
package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core_data.Result
import com.example.taskzz.task_api.TaskListRepository
import com.example.taskzz.task_api.TaskListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class ProdGetTasksForDateUseCase  @Inject constructor(
    private val taskListRepository: TaskListRepository
): GetTasksForDateUseCase {
    override fun invoke(date: LocalDate): Flow<TaskListResult> {
        val dateMillis = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val incompleteTaskFlow = taskListRepository.fetchTasksForDate(
            dateMillis = dateMillis, completed = false
        )
        val completeTaskFlow = taskListRepository.fetchTasksForDate(
            dateMillis = dateMillis, completed = true
        )

        return incompleteTaskFlow.combineTransform(completeTaskFlow){incomplete, complete ->

            if(incomplete is Result.Success && complete is Result.Success){
                val result = Result.Success( incomplete.data + complete.data)
                emit(result)
            }else{
                emit(Result.Error(Throwable("Error requesting tasks for date ${date}")))
            }
        }
    }

}
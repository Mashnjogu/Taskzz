package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import com.example.taskzz.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import java.time.LocalDate
import javax.inject.Inject

class ProdGetTasksForDateUseCase  @Inject constructor(
    private val taskListRepository: TaskListRepository
): GetTasksForDateUseCase {
    override fun invoke(date: LocalDate): Flow<TaskListResult> {
        val incompleteTaskFlow = taskListRepository.fetchTasksForDate(date = date, completed = false)
        val completeTaskFlow = taskListRepository.fetchTasksForDate(date = date, completed = true)

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
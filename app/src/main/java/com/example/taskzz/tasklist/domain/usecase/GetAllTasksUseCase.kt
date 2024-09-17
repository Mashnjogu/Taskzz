package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import kotlinx.coroutines.flow.Flow

/*
Usecases should have Result classes associated with them
 */
interface GetAllTasksUseCase {

     operator fun invoke(): Flow<Result<List<Task>>>
}
package com.example.taskzz.home.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.home.tasklist.domain.model.Task

/*
Usecases should have Result classes associated with them
 */
interface GetAllTasksUseCase {

    suspend operator fun invoke(): Result<List<Task>>
}
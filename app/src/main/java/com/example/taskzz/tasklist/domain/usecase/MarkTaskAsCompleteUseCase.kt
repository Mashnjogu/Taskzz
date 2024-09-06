package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.core.models.Task

interface MarkTaskAsCompleteUseCase {

    suspend operator fun invoke(task: Task) : Result<Unit>
}
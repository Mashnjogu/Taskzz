package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.tasklist.domain.model.Task

interface MarkTaskAsCompleteUseCase {

    suspend operator fun invoke(tak: Task) :Result<Unit>
}
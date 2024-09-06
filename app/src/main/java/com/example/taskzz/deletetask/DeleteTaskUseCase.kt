package com.example.taskzz.deletetask

import com.example.taskzz.core.models.Task


interface DeleteTaskUseCase {

    suspend operator fun invoke(task: Task): Result<Unit>
}
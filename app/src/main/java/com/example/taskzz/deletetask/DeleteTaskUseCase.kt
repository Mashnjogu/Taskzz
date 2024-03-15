package com.example.taskzz.deletetask

import com.example.taskzz.tasklist.domain.model.Task

interface DeleteTaskUseCase {

    suspend operator fun invoke(task: Task): Result<Unit>
}
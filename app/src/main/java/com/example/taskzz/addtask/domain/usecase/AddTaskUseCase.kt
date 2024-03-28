package com.example.taskzz.addtask.domain.usecase

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task

/*
Given a new task store it in the user task list
 */
interface AddTasksUseCase {
        suspend operator fun invoke(task: Task): Result<Unit>
}
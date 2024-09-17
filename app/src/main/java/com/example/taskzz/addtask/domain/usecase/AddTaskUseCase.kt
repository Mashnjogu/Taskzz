package com.example.taskzz.addtask.domain.usecase

import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.core.models.Task

/*
Given a new task store it in the user task list
 */
interface AddTasksUseCase {
        suspend operator fun invoke(task: Task): AddTaskResult
}
package com.example.taskzz.addtask.domain

import com.example.taskzz.tasklist.domain.model.Task

/*
Given a new task store it in the user task list
 */
interface AddTaskUseCase {

    suspend operator fun invoke(task: Task): Result<Unit>
}
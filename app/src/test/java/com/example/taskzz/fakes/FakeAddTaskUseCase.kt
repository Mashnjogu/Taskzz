package com.example.taskzz.fakes

import com.example.taskzz.addtask.domain.model.AddTaskResult
import com.example.taskzz.addtask.domain.usecase.AddTasksUseCase
import com.example.taskzz.tasklist.domain.model.Task
import io.mockk.coEvery
import io.mockk.mockk

class FakeAddTaskUseCase {

    val mock: AddTasksUseCase = mockk()

    fun mockResultForTask(
        task: Task,
        result: AddTaskResult
    ){
        coEvery {
            mock.invoke(any())
        }returns result
    }
}
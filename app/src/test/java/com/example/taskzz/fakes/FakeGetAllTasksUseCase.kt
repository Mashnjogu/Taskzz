package com.example.taskzz.fakes

import com.example.taskzz.core.data.Result
import com.example.taskzz.home.tasklist.domain.model.Task
import com.example.taskzz.home.tasklist.domain.usecase.GetAllTasksUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk

class FakeGetAllTasksUseCase {
    val mock: GetAllTasksUseCase = mockk()

    fun mockResult(response: Result<List<Task>>){
        coEvery {
            mock.invoke()
        }returns response
    }
}





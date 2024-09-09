package com.example.taskzz.fakes

import com.example.taskzz.core.data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.tasklist.domain.usecase.GetAllTasksUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow

class FakeGetAllTasksUseCase {
    val mock: GetAllTasksUseCase = mockk()

    fun mockResult(response: Flow<Result<List<Task>>>){
        coEvery {
            mock.invoke()
        }returns response
    }
}





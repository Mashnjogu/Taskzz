package com.example.taskzz.fakes

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf

class FakeTaskListRepository {

    val mock: TaskListRepository = mockk()

    fun mockFetchAllTasks(response: Result<List<Task>>){
        coEvery {
            mock.fetchAllTasks()
        }returns flowOf(response)
    }
}
package com.example.taskzz.fakes

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate

class FakeTaskListRepository {

    val mock: TaskListRepository = mockk()

    fun mockTasksForDateResults(date: LocalDate, response: Result<List<Task>>){
        coEvery {
            mock.fetchTasksForDate(date)
        }returns flowOf(response)
    }
}
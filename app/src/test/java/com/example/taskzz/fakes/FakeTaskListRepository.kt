package com.example.taskzz.fakes

import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api.TaskListRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.ZoneId

class FakeTaskListRepository {

    val mock: TaskListRepository = mockk()

    fun mockTasksForDateResults(date: LocalDate, response: Result<List<Task>>, completed: Boolean){
        val dateMillis = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        coEvery {
            mock.fetchTasksForDate(dateMillis=dateMillis, completed = completed)
        }returns flowOf(response)
    }
}
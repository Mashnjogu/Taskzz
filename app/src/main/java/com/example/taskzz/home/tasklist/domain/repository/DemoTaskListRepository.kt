package com.example.taskzz.home.tasklist.domain.repository

import com.example.taskzz.core.data.Result
import com.example.taskzz.home.tasklist.domain.model.Task
import kotlinx.coroutines.delay
import javax.inject.Inject

class DemoTaskListRepository @Inject constructor(): TaskListRepository {
    override suspend fun fetchAllTasks(): Result<List<Task>> {
        @Suppress("BlockingMethodInNonBlockingContext")
        delay(2000)
        val tasks = (1..10).map { index ->
            Task(
                description = "Task $index",
            )
        }

        return Result.Success(tasks)
    }

}
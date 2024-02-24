package com.example.taskzz.home.tasklist.domain.repository

import com.example.taskzz.home.tasklist.domain.model.Task
import com.example.taskzz.core.data.Result
interface  TaskListRepository {
    suspend fun fetchAllTasks(): Result<List<Task>>
}
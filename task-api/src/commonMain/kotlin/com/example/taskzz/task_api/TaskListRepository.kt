package com.example.taskzz.task_api

import com.example.taskzz.core.models.Task
import kotlinx.coroutines.flow.Flow
import  com.example.taskzz.core_data.Result


typealias TaskListResult = Result<List<Task>>

interface  TaskListRepository {

    fun fetchAllTasks(): Flow<TaskListResult>

    fun fetchTasksForDate(dateMillis: Long, completed: Boolean): Flow<TaskListResult >

    suspend fun addTask(task: Task): Result<Unit>

    suspend fun deleteTask(task: Task): Result<Unit>

    suspend fun updateTask(task: Task): Result<Unit>

}
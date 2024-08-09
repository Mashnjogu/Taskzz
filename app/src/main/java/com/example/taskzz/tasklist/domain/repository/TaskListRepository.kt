package com.example.taskzz.tasklist.domain.repository

import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.core.data.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

typealias TaskListResult = Result<List<Task>>

interface  TaskListRepository {

    fun fetchAllTasks(): Flow<TaskListResult >

    fun fetchTasksForDate(date: LocalDate): Flow<TaskListResult >

    suspend fun addTask(task: Task): Result<Unit>

    suspend fun deleteTask(task: Task): Result<Unit>

    suspend fun markAsComplete(task: Task): Result<Unit>

}
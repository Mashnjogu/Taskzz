package com.example.taskzz.tasklist.domain.repository


import com.example.taskzz.core.data.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import com.example.taskzz.core.models.Task

typealias TaskListResult = Result<List<Task>>

interface  TaskListRepository {

    fun fetchAllTasks(): Flow<TaskListResult >

    fun fetchTasksForDate(date: LocalDate, completed: Boolean): Flow<TaskListResult >

    suspend fun addTask(task: Task): Result<Unit>

    suspend fun deleteTask(task: Task): Result<Unit>

    suspend fun updateTask(task: Task): Result<Unit>



}
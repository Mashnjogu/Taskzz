package com.example.taskzz.tasklist.domain.repository

import android.util.Log
import com.example.taskzz.core.data.Result

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

//class DemoTaskListRepository @Inject constructor(): TaskListRepository {
//
//    init {
//        Log.d("DemoTaskListRepository", "Creating the repository")
//    }
//
//    private val tasks: MutableList<Task> =  (1..10).map { index ->
//        Task(
//            description = "Task $index",
//        )
//    }.toMutableList()
//
//    private val taskFlow = MutableStateFlow(tasks)
//    override fun fetchAllTasks(): Flow<Result<List<Task>>> {
//        return taskFlow.map {tasks ->
//            Result.Success(tasks)
//        }
//    }
//
//    override fun fetchTasksForDate(date: LocalDate): Flow<TaskListResult> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun addTask(task: Task): Result<Unit> {
//        tasks.add(0,task)
//
//        taskFlow.value = tasks
//        return Result.Success(Unit)
//    }
//
//    override suspend fun deleteTask(task: Task): Result<Unit> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun markAsComplete(task: Task): Result<Unit> {
//        TODO("Not yet implemented")
//    }
//
//}
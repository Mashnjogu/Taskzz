package com.example.taskzz.task_api_test

import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api.TaskListRepository
import com.example.taskzz.task_api.TaskListResult
import kotlinx.coroutines.flow.Flow
import com.example.taskzz.core_data.Result

/**
 * A concrete implementation of [TaskListRepository] that allows to caller to mock and verify calls to this repo.
 */

typealias TasksForDateInput = Pair<Long, Boolean>

class FakeTaskRepository : TaskListRepository{

    lateinit var allTasksResult: Flow<TaskListResult>

    val tasksForDateResults: MutableMap<TasksForDateInput, Flow<TaskListResult>> = mutableMapOf()

    val addTasksResult: MutableMap<Task, Result<Unit>> = mutableMapOf()

    val updateTasksResult: MutableMap<Task, Result<Unit>> = mutableMapOf()

    override fun fetchAllTasks(): Flow<TaskListResult> {
        return allTasksResult
    }

    override fun fetchTasksForDate(
        dateMillis: Long,
        completed: Boolean
    ): kotlinx.coroutines.flow.Flow<TaskListResult> {
        val inputPair = Pair(dateMillis, completed)
        return tasksForDateResults[inputPair]!!
    }

    override suspend fun addTask(task: Task): Result<Unit> {
        return addTasksResult[task]!!
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task): Result<Unit> {
        return updateTasksResult[task]!!
    }

}
package com.example.taskzz.core.data.local

import com.example.taskzz.core_data.Result
import com.example.taskzz.core.models.Task
import com.example.taskzz.task_api.TaskListRepository
import com.example.taskzz.task_api.TaskListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/*
This repository is meant to avoid tight coupling with room db in which one can easily change
databases
 */
class RoomTaskListRepository @Inject constructor(
    private val taskDAO: TaskDAO
): TaskListRepository {
    override fun fetchAllTasks(): Flow<Result<List<Task>>> {
        return taskDAO.fetchAllTasks().map {taskList ->
            Result.Success(taskList.toDomainTaskList())
        }
    }

    override fun fetchTasksForDate(dateMillis: Long, completed: Boolean): Flow<TaskListResult> {
        val localDate = Instant.ofEpochMilli(dateMillis).atZone(ZoneId.systemDefault()).toLocalDate()
        return taskDAO.fetchTasksForDate(
            date = localDate.toPersistableDateString(),
            completed = completed
        )
            .map {taskList ->
                Result.Success(taskList.toDomainTaskList())
            }
    }

    override suspend fun addTask(task: Task): Result<Unit> {
        taskDAO.insertTask(task.toPersistableTask())

        return Result.Success(Unit)
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task): Result<Unit> {
        taskDAO.updateTask(task.toPersistableTask())
        return Result.Success(Unit)
    }

}

private fun List<PersistableTask>.toDomainTaskList(): List<Task>{
    return this.map(PersistableTask::toTask)
}

private const val PERSISTED_DATE_FORMAT = "yyyy-MM-dd"
private val persistedDateFormatter = DateTimeFormatter.ofPattern(PERSISTED_DATE_FORMAT)

private fun LocalDate.toPersistableDateString(): String {
    return persistedDateFormatter.format(this)
}

private fun String.parsePersistableDateString(): LocalDate{
    return LocalDate.parse(this, persistedDateFormatter)
}

private fun PersistableTask.toTask(): Task {
    return Task(
        id = this.id,
        description = this.description,
        scheduledDateMillis = this.scheduledDate.parsePersistableDateString()
            .atStartOfDay()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli(),
        completed = this.completed
    )
}

private fun Task.toPersistableTask(): PersistableTask {
    val scheduledDate = Instant.ofEpochMilli(this.scheduledDateMillis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    return PersistableTask(
        id = this.id,
        description = this.description,
        scheduledDate = scheduledDate.toPersistableDateString(),
        autoMigrationName = "",
        completed = this.completed
    )
}
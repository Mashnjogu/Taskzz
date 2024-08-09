package com.example.taskzz.core.data.local

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
import com.example.taskzz.tasklist.domain.repository.TaskListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import javax.inject.Inject

/*
This repository is meant to avoid tight coupling with room db in which one can easily change
databases
 */
class RoomTaskListRepository @Inject constructor(
    private val taskDAO: TaskDAO
): TaskListRepository{
    override fun fetchAllTasks(): Flow<Result<List<Task>>> {
        return taskDAO.fetchAllTasks().map {taskList ->
            Result.Success(taskList.toDomainTaskList())
        }
    }

    override fun fetchTasksForDate(date: LocalDate): Flow<TaskListResult> {
        return taskDAO.fetchTasksForDate(date = date.toPersistableDateString())
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

    override suspend fun markAsComplete(task: Task): Result<Unit> {
        TODO("Not yet implemented")
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
        scheduledDate = this.scheduledDate.parsePersistableDateString(),
    )
}

private fun Task.toPersistableTask(): PersistableTask {
    return PersistableTask(
        id = this.id,
        description = this.description,
        scheduledDate = this.scheduledDate.toPersistableDateString(),
        autoMigrationName = ""
    )
}
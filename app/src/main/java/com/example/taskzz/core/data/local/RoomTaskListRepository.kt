package com.example.taskzz.core.data.local

import com.example.taskzz.core.data.Result
import com.example.taskzz.tasklist.domain.model.Task
import com.example.taskzz.tasklist.domain.repository.TaskListRepository
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
            val domainTasks = taskList.map(PersistableTask::toTask)

            Result.Success(domainTasks)
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
private const val PERSISTED_DATE_FORMAT = "yyyy-MM-dd"
private val persistedDateFormatter = DateTimeFormatter.ofPattern(PERSISTED_DATE_FORMAT)

private fun PersistableTask.toTask(): Task {
    return Task(
        id = this.id,
        description = this.description,
        scheduledDate = LocalDate.parse(this.scheduledDate, persistedDateFormatter),
    )
}

private fun Task.toPersistableTask(): PersistableTask {
    return PersistableTask(
        id = this.id,
        description = this.description,
        scheduledDate = persistedDateFormatter.format(this.scheduledDate),
        autoMigrationName = ""
    )
}
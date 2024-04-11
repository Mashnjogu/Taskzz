package com.example.taskzz.tasklist.ui

import com.example.taskzz.tasklist.domain.model.Task
import java.time.format.DateTimeFormatter

/*
Creates a user friendly representation of the [Task] model
*/
data class TaskDisplayModel(
    val taskId: String = "",
    val description: String,
    val scheduledDate: String
)

fun Task.toDisplayModel(): TaskDisplayModel {

    val friendlyDatePattern = "MMM dd yyyy"
    val friendlyDateFormatter = DateTimeFormatter.ofPattern(friendlyDatePattern)

    return TaskDisplayModel(
        description = description,
        scheduledDate =friendlyDateFormatter.format(this.scheduledDate)
    )
}


package com.example.taskzz.tasklist.ui

/*
Creates a user friendly representation of the [Task] model
*/
data class TaskDisplayModel(
    val description: String,
    val scheduledDate: String,
    val onRescheduleClicked: () -> Unit,
    val onDoneClicked: () -> Unit
)

//fun Task.toDisplayModel(): TaskDisplayModel {
//
//    val friendlyDatePattern = "MMM dd yyyy"
//    val friendlyDateFormatter = DateTimeFormatter.ofPattern(friendlyDatePattern)
//
//    return TaskDisplayModel(
//        description = description,
//        scheduledDate =friendlyDateFormatter.format(this.scheduledDate),
//        onTaskClicked = {}
//    )
//}


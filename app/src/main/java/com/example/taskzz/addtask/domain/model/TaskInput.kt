package com.example.taskzz.addtask.domain.model

import java.time.LocalDate

//info required when creating a task
data class TaskInput(
    val description: String = "",
    val scheduledDate: LocalDate = LocalDate.now()
)

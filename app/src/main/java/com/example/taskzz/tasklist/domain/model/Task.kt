package com.example.taskzz.tasklist.domain.model

import java.time.LocalDate

data class Task(
    val description: String,
    val scheduledDate: LocalDate = LocalDate.now()
)


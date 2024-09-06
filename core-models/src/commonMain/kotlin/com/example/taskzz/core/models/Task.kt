package com.example.taskzz.core.models

data class Task(
    val id: String,
    val description: String,
    val scheduledDateMillis: Long,
    val completed: Boolean,
)

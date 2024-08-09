package com.example.taskzz.tasklist.ui

import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import java.time.LocalDate

data class TaskListViewState(
    val showLoading: Boolean = true,
    val tasks: List<Task>? = null,
    val errorMessage: UiText? = null,
    val selectedDate: LocalDate = LocalDate.now()
)

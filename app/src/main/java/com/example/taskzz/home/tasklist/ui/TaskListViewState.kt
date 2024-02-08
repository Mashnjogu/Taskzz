package com.example.taskzz.home.tasklist.ui

import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.home.tasklist.domain.model.Task

sealed class TaskListViewState{
    object Loading: TaskListViewState()

    data class Loaded(val tasks: List<Task>): TaskListViewState()

    data class Error(
        val errorMessage: UiText
    ): TaskListViewState()
}

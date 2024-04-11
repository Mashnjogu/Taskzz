package com.example.taskzz.tasklist.ui

import com.example.taskzz.core.ui.components.UiText

sealed class TaskListViewState{
    object Loading: TaskListViewState()

    data class Loaded(val tasks: List<TaskDisplayModel>): TaskListViewState()

    data class Error(
        val errorMessage: UiText
    ): TaskListViewState()
}

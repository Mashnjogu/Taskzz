package com.example.taskzz.addtask.ui

import androidx.lifecycle.ViewModel
import com.example.taskzz.addtask.domain.usecase.AddTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@HiltViewModel
class AddTasksViewModel @Inject constructor(
    private val addTasksUseCase: AddTasksUseCase
) : ViewModel(){

    private val _viewState: MutableStateFlow<AddTaskViewState> = MutableStateFlow(AddTaskViewState.Initial)
    val viewState: StateFlow<AddTaskViewState> = _viewState
}
package com.example.taskzz.core.di

import com.example.taskzz.addtask.domain.usecase.AddTasksUseCase
import com.example.taskzz.addtask.domain.usecase.ProdAddTaskUseCase
import com.example.taskzz.tasklist.domain.usecase.GetAllTasksUseCase
import com.example.taskzz.tasklist.domain.usecase.ProdGetAllTasksUseCase
import com.example.taskzz.login.domain.usecase.CredentialsLoginUseCase
import com.example.taskzz.login.domain.usecase.ProdCredentialsLoginUseCase
import com.example.taskzz.tasklist.domain.usecase.GetTasksForDateUseCase
import com.example.taskzz.tasklist.domain.usecase.MarkTaskAsCompleteUseCase
import com.example.taskzz.tasklist.domain.usecase.ProdGetTasksForDateUseCase
import com.example.taskzz.tasklist.domain.usecase.ProdMarkTaskAsCompleteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule{

    @Binds
    abstract fun bindCredentialsLoginUseCase(
        credentialsLoginUseCase: ProdCredentialsLoginUseCase
    ): CredentialsLoginUseCase

    @Binds
    abstract fun bindGetAllTasksUseCase(
        getAllTasksUseCase: ProdGetAllTasksUseCase
    ): GetAllTasksUseCase

   @Binds
   abstract fun bindAddTaskUseCase(
       addTaskUseCase: ProdAddTaskUseCase
   ): AddTasksUseCase

   @Binds
   abstract fun bindGetTasksForDateUseCase(
        getTaskForDateUseCase: ProdGetTasksForDateUseCase
    ): GetTasksForDateUseCase

   @Binds
   abstract fun bindMarkTaskAsCompleteUseCase(
       markAsCompleteUseCase: ProdMarkTaskAsCompleteUseCase
   ): MarkTaskAsCompleteUseCase
}
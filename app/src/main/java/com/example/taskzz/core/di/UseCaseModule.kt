package com.example.taskzz.core.di

import com.example.taskzz.tasklist.domain.usecase.GetAllTasksUseCase
import com.example.taskzz.tasklist.domain.usecase.ProdGetAllTasksUseCase
import com.example.taskzz.login.domain.usecase.CredentialsLoginUseCase
import com.example.taskzz.login.domain.usecase.ProdCredentialsLoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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

}
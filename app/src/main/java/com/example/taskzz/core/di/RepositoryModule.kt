package com.example.taskzz.core.di

import com.example.taskzz.login.domain.repository.DemoLoginRepository
import com.example.taskzz.login.domain.repository.DemoTokenRepository
import com.example.taskzz.login.domain.repository.LoginRepository
import com.example.taskzz.login.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    abstract fun bindTokenRepository(tokenRepository: DemoTokenRepository): TokenRepository

    @Binds
    abstract fun bindLoginRepository(loginRepository: DemoLoginRepository): LoginRepository
}
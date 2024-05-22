package com.example.taskzz.core.di

import android.content.Context
import androidx.room.Room
import com.example.taskzz.core.data.local.TaskDAO
import com.example.taskzz.core.data.local.TaskzzDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideTaskzzDatabase(
        @ApplicationContext
        applicationContext: Context,
    ): TaskzzDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TaskzzDatabase::class.java,
            "taskzz-database.db",
        ).build()
    }

    @Provides
    fun provideTaskDAO(
        database: TaskzzDatabase,
    ): TaskDAO {
        return database.taskDao()
    }
}
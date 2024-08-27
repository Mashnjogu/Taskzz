package com.example.taskzz.core.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersistableTask::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3),
    ]
)
abstract class TaskzzDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}
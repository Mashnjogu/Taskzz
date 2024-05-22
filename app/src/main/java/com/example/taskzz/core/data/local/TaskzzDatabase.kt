package com.example.taskzz.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersistableTask::class],
    version = 1,
    exportSchema = true,
)
abstract class TaskzzDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}
package com.example.taskzz.core.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class PersistableTask(
    @PrimaryKey
    val id: String,
    val description: String,
    val scheduledDate: String,
    @ColumnInfo(defaultValue = "Auto_migration column")
    val autoMigrationName: String,
    val completed: Boolean

)
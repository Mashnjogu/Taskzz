package com.example.taskzz.tasklist.domain.usecase

import com.example.taskzz.core_data.Result

interface RescheduleTaskUseCase {

    suspend operator fun invoke(taskId: String): Result<Unit>
}
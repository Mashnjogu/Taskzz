package com.example.taskzz.addtask.domain.model


/**
 * A collection of possible view states for the add task UI.
 */
sealed class AddTaskResult {

    object  Success: AddTaskResult()

    sealed class Failure: AddTaskResult(){

        data class InvalidInput(
            val emptyDescription: Boolean,
            val unknownDateInput: Boolean
        ): Failure()

        object Unkown: Failure()
    }
}
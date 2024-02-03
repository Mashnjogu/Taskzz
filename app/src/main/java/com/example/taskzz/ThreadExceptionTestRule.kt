package com.example.taskzz

import org.junit.AssumptionViolatedException
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
A custom test watcher that sets the default uncaught exception handler so that any exceptions
inside of a viewmodel scope will actually fail a test.
 */
class ThreadExceptionTestRule: TestWatcher() {

    private var previousHandler: Thread.UncaughtExceptionHandler? = null
    override fun starting(description: Description?) {
        super.starting(description)

        previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler {_,t->
            throw t
        }
    }

    override fun finished(description: Description?) {
        Thread.setDefaultUncaughtExceptionHandler(previousHandler)
    }
}
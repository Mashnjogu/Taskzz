package com.example.taskzz

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.taskzz.tasklist.domain.model.Task
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import com.example.taskzz.tasklist.ui.TaskListContent
import com.example.taskzz.tasklist.ui.TaskListViewState

class TaskListContentTest{
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val testTask = Task(
        id = "Test ID",
        description = "Test task",
        scheduledDate = LocalDate.now(),
        completed = false
    )

    @Test
    fun renderWithNoTasks(){

        val viewState = TaskListViewState(
            showLoading = false,
            incompleteTasks = emptyList(),
            completedTasks = emptyList()
        )

        composeTestRule.setContent {
            TaskListContent(
                viewState = viewState,
                onRescheduleClicked = {},
                onDoneClicked = {},
                onAddButtonClicked = { /*TODO*/ },
                onPreviousDateButtonClicked = { /*TODO*/ }) {
                
            }
        }

        val noTasksScheduledLabel = composeTestRule.activity.getString(R.string.no_tasks_scheduled_label)
        composeTestRule.onNodeWithText(
            text = noTasksScheduledLabel
        ).assertIsDisplayed()
    }

    @Test
    fun renderWithNoIncompleteTasks(){
        val viewState = TaskListViewState(
            showLoading = false,
            incompleteTasks = emptyList(),
            completedTasks = listOf(testTask)
        )

        composeTestRule.setContent {
            TaskListContent(
                viewState = viewState,
                onRescheduleClicked = {},
                onDoneClicked = {},
                onAddButtonClicked = { /*TODO*/ },
                onPreviousDateButtonClicked = { /*TODO*/ }) {

            }
        }

        val noIncompleteTasksLabel = composeTestRule.activity.getString(R.string.no_incomplete_task_label)
        //assert no incomplete task
        composeTestRule.onNodeWithText(
            text = noIncompleteTasksLabel
        ).assertIsDisplayed()

        //assert complete tasks rendered
        composeTestRule.onNodeWithText(
            text = testTask.description
        ).assertIsDisplayed()
    }

    @Test
    fun renderWithNoCompleteTasks(){
        val viewState = TaskListViewState(
            showLoading = false,
            incompleteTasks = listOf(testTask),
            completedTasks = emptyList()
        )

        composeTestRule.setContent {
            TaskListContent(
                viewState = viewState,
                onRescheduleClicked = {},
                onDoneClicked = {},
                onAddButtonClicked = { /*TODO*/ },
                onPreviousDateButtonClicked = { /*TODO*/ }) {

            }
        }

        val noCompletedTasksLabel = composeTestRule.activity.getString(R.string.no_completed_tasks_label)
        //assert no incomplete task
        composeTestRule.onNodeWithText(
            text = noCompletedTasksLabel
        ).assertIsDisplayed()

        //assert complete tasks rendered
        composeTestRule.onNodeWithText(
            text = testTask.description
        ).assertIsDisplayed()
    }
}
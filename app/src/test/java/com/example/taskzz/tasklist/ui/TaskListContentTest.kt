package com.example.taskzz.tasklist.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class TaskListContentTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickPreviousDateButton(){
        var hasClickedPreviousDate = false

        composeTestRule.setContent {
            TaskListContent(
                viewState = TaskListViewState(),
                onRescheduleClicked = {},
                onDoneClicked = {},
                onAddButtonClicked = { /*TODO*/ },
                onPreviousDateButtonClicked = {
                    hasClickedPreviousDate = true
                },
                onNextDateButtonClicked = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("View Previous Date's Task")
            .performClick()

        assertThat(hasClickedPreviousDate).isTrue()
    }

    @Test
    fun clickNextDateButton(){
        var hasClickedNextDate = false

        composeTestRule.setContent {
            TaskListContent(
                viewState = TaskListViewState(),
                onRescheduleClicked = {},
                onDoneClicked = {},
                onAddButtonClicked = { /*TODO*/ },
                onPreviousDateButtonClicked = {
                },
                onNextDateButtonClicked = {
                    hasClickedNextDate = true
                }
            )
        }

        composeTestRule.onNodeWithContentDescription("View Next Date")
            .performClick()

        assertThat(hasClickedNextDate).isTrue()
    }
}
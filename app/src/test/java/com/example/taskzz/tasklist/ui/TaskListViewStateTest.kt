package com.example.taskzz.tasklist.ui

import com.example.taskzz.core.ui.components.UiText
import org.junit.Test
import java.time.LocalDate
import com.example.taskzz.R
import com.example.taskzz.core.utils.getSuffixForDateOfMonth
import com.google.common.truth.Truth.assertThat
import java.time.format.DateTimeFormatter


class TaskListViewStateTest{

    @Test
    fun parseDateStringForToday(){
        val today = LocalDate.now()
        val viewState = TaskListViewState(
            selectedDate = today
        )

        val expectedString = UiText.ResourceText(R.string.today)

        assertThat(viewState.selectedDateString).isEqualTo(expectedString)
    }

    @Test
    fun parseDateStringForYesterday(){
        val yesterday = LocalDate.now().minusDays(1)
        val viewState = TaskListViewState(
            selectedDate = yesterday
        )

        val expectedString = UiText.ResourceText(R.string.yesterday)

        assertThat(viewState.selectedDateString).isEqualTo(expectedString)
    }

    @Test
    fun parseDateStringForTomorrow(){
        val tomorrow = LocalDate.now().plusDays(1)
        val viewState = TaskListViewState(
            selectedDate = tomorrow
        )

        val expectedString = UiText.ResourceText(R.string.tomorrow)

        assertThat(viewState.selectedDateString).isEqualTo(expectedString)
    }

    @Test
    fun parseDateStringForFuture(){
        val twoDaysFromNow = LocalDate.now().plusDays(2)
        val viewState = TaskListViewState(
            selectedDate = twoDaysFromNow
        )

        val expectedDateFormat = "MMM d"
        val expectedSuffix = twoDaysFromNow.getSuffixForDateOfMonth()
        val parseDateString = DateTimeFormatter.ofPattern(expectedDateFormat.format(twoDaysFromNow))
        val expectedDateString = "$parseDateString$expectedSuffix"
        val expectedString = UiText.StringText(expectedDateString)

        assertThat(viewState.selectedDateString).isEqualTo(expectedString)
    }


}
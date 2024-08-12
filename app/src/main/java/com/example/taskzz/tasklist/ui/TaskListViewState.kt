package com.example.taskzz.tasklist.ui

import com.example.taskzz.core.ui.components.UiText
import com.example.taskzz.tasklist.domain.model.Task
import java.time.LocalDate
import com.example.taskzz.R
import java.time.format.DateTimeFormatter

data class TaskListViewState(
    val showLoading: Boolean = true,
    val tasks: List<Task>? = null,
    val errorMessage: UiText? = null,
    val selectedDate: LocalDate = LocalDate.now()
){

    val selectedDateString: UiText
        get(){
            val today = LocalDate.now()
            val isToday = (selectedDate == today)
            val isTomorrow = (selectedDate == LocalDate.now().plusDays(1))
            val isYesterday = (selectedDate == LocalDate.now().minusDays(1))

            return when{
                isToday -> { UiText.ResourceText(R.string.today)}
                isTomorrow -> { UiText.ResourceText(R.string.tomorrow)}
                isYesterday -> { UiText.ResourceText(R.string.yesterday)}
                else -> {
                    val uiDateFormat = "MMM d"
                    val uiString = DateTimeFormatter.ofPattern(uiDateFormat).format(selectedDate)
                    UiText.StringText(uiString)
                }
            }
        }

}

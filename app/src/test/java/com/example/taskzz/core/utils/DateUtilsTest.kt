package com.example.taskzz.core.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class DateUtilsTest{

    @Test
    fun parseStSuffixes(){
        listOf(1, 21, 31)
            .convertToSuffix()
            .forEach{ suffix ->
                assertThat(suffix).isEqualTo("st")
            }
    }

    @Test
    fun parseNdSuffixes(){
        listOf(2, 22)
            .convertToSuffix()
            .forEach{ suffix ->
                assertThat(suffix).isEqualTo("nd")
            }
    }

    @Test
    fun parseRdSuffixes(){
        listOf(3, 23)
            .convertToSuffix()
            .forEach{ suffix ->
                assertThat(suffix).isEqualTo("rd")
            }
    }

    @Test
    fun parseThSuffixes(){
        listOf(
            (4..9),
            (10..20),
            (24..30),
        )
            .flatten()
            .convertToSuffix()
            .forEach{ suffix ->
                assertThat(suffix).isEqualTo("th")
            }
    }
}

private fun List<Int>.convertToSuffix(): List<String>{
    return this.map { day ->
        LocalDate.of(2024, Month.AUGUST, day).getSuffixForDateOfMonth()
    }
}


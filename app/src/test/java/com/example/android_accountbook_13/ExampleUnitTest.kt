package com.example.android_accountbook_13

import com.example.android_accountbook_13.utils.Date
import com.example.android_accountbook_13.utils.getDayOfWeek
import org.junit.Assert.assertEquals
import org.junit.Test
import org.threeten.bp.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, getDayOfWeek(Date(2022,7,31)))
    }
}
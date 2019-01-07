package com.gaelmarhic.presentation.common.extensions

import com.gaelmarhic.presentation.common.constants.Constants.Companion.LONG_DATE_FORMAT
import com.gaelmarhic.presentation.common.constants.Constants.Companion.SHORT_DATE_FORMAT
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class DatesTest {

    @Test
    fun `Test to convert a timestamp and check that it went ok - Case 1`() {

        val timestampToTest = 1515715200L
        val shortFormattedDate = timestampToTest.getFormattedDateFromTimestamp(SHORT_DATE_FORMAT)
        val longFormattedDate = timestampToTest.getFormattedDateFromTimestamp(LONG_DATE_FORMAT)
        assertEquals(shortFormattedDate, "12 Jan")
        assertEquals(longFormattedDate, "2018-01-12")
    }

    @Test
    fun `Test to convert a timestamp and check that it went ok - Case 2`() {

        val timestampToTest = 1520380800L
        val shortFormattedDate = timestampToTest.getFormattedDateFromTimestamp(SHORT_DATE_FORMAT)
        val longFormattedDate = timestampToTest.getFormattedDateFromTimestamp(LONG_DATE_FORMAT)
        assertEquals(shortFormattedDate, "07 Mar")
        assertEquals(longFormattedDate, "2018-03-07")
    }

    @Test
    fun `Test to convert a timestamp and check that it went ok - Case 3`() {

        val timestampToTest = 1523664000L
        val shortFormattedDate = timestampToTest.getFormattedDateFromTimestamp(SHORT_DATE_FORMAT)
        val longFormattedDate = timestampToTest.getFormattedDateFromTimestamp(LONG_DATE_FORMAT)
        assertEquals(shortFormattedDate, "14 Apr")
        assertEquals(longFormattedDate, "2018-04-14")
    }

    @Test
    fun `Test to convert a timestamp and check that it went ok - Case 4`() {

        val timestampToTest = 1534982400L
        val shortFormattedDate = timestampToTest.getFormattedDateFromTimestamp(SHORT_DATE_FORMAT)
        val longFormattedDate = timestampToTest.getFormattedDateFromTimestamp(LONG_DATE_FORMAT)
        assertEquals(shortFormattedDate, "23 Aug")
        assertEquals(longFormattedDate, "2018-08-23")
    }

    @Test
    fun `Test to convert a timestamp and check that it went ok - Case 5`() {

        val timestampToTest = 1541721600L
        val shortFormattedDate = timestampToTest.getFormattedDateFromTimestamp(SHORT_DATE_FORMAT)
        val longFormattedDate = timestampToTest.getFormattedDateFromTimestamp(LONG_DATE_FORMAT)
        assertEquals(shortFormattedDate, "09 Nov")
        assertEquals(longFormattedDate, "2018-11-09")
    }
}

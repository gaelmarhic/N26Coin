package com.gaelmarhic.presentation.common.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class AmountsTest {

    companion object {

        const val THOUSANDS_SEPARATOR = ','
    }

    @Test
    fun `Test that an amount with only 3 digits has no thousand separator`() {

        val amountToTest = 100L
        assertEquals(amountToTest.applyThousandsSeparator(THOUSANDS_SEPARATOR), "100")
    }

    @Test
    fun `Test that an amount with 4 digits has one thousand separator`() {

        val amountToTest = 1000L
        assertEquals(amountToTest.applyThousandsSeparator(THOUSANDS_SEPARATOR), "1,000")
    }

    @Test
    fun `Test that an amount with 6 digits only has one thousand separator`() {

        val amountToTest = 100000L
        assertEquals(amountToTest.applyThousandsSeparator(THOUSANDS_SEPARATOR), "100,000")
    }

    @Test
    fun `Test that an amount with 7 digits has two thousand separator`() {

        val amountToTest = 1000000L
        assertEquals(amountToTest.applyThousandsSeparator(THOUSANDS_SEPARATOR), "1,000,000")
    }

    @Test
    fun `Test that an amount with 9 digits only has two thousand separator`() {

        val amountToTest = 100000000L
        assertEquals(amountToTest.applyThousandsSeparator(THOUSANDS_SEPARATOR), "100,000,000")
    }

    @Test
    fun `Test than an amount with 10 digits has three thousand separator`() {

        val amountToTest = 1000000000L
        assertEquals(amountToTest.applyThousandsSeparator(THOUSANDS_SEPARATOR), "1,000,000,000")
    }
}

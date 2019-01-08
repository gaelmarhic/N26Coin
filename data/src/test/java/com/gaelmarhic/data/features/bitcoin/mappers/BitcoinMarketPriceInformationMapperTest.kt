package com.gaelmarhic.data.features.bitcoin.mappers

import com.gaelmarhic.data.common.exceptions.EssentialParamMissingException
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceInformationRaw
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceRaw
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class BitcoinMarketPriceInformationMapperTest {

    /**
     * Rule that allows us to verify that our code throws a specific exception.
     */
    @get:Rule
    val thrownException: ExpectedException = ExpectedException.none()

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var mapper: BitcoinMarketPriceInformationMapper

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the class that we want to test.
        mapper = BitcoinMarketPriceInformationMapper()
    }

    @Test
    fun`Test the mapping of the whole object`() {

        // Creating the object that we will use for the mapping.
        val bitcoinMarketPriceInformationRaw = instantiateBitcoinMarketPriceInformationRawObject()

        // Triggering the function that we want to test.
        val bitcoinMarketPriceInformation = mapper.apply(bitcoinMarketPriceInformationRaw)

        // Assertions
        assertEquals(bitcoinMarketPriceInformation.status, bitcoinMarketPriceInformationRaw.status)
        assertEquals(bitcoinMarketPriceInformation.name, bitcoinMarketPriceInformationRaw.name)
        assertEquals(bitcoinMarketPriceInformation.unit, bitcoinMarketPriceInformationRaw.unit)
        assertEquals(bitcoinMarketPriceInformation.period, bitcoinMarketPriceInformationRaw.period)
        assertEquals(bitcoinMarketPriceInformation.description, bitcoinMarketPriceInformationRaw.description)
        assertEquals(bitcoinMarketPriceInformation.values.size, bitcoinMarketPriceInformationRaw.values?.size)
        assertEquals(bitcoinMarketPriceInformation.values[0].timestamp, bitcoinMarketPriceInformationRaw.values?.get(0)?.timestamp)
        assertEquals(bitcoinMarketPriceInformation.values[0].amount, bitcoinMarketPriceInformationRaw.values?.get(0)?.amount)
    }

    @Test
    fun`Check that the EssentialParamMissingException is thrown when the mandatory params are missing`() {

        // Setting up the Exception that we are expecting to get.
        thrownException.expect(EssentialParamMissingException::class.java)
        thrownException.expectMessage("status, name, unit, period, description, values")

        // Triggering the function that we want to test.
        mapper.apply(BitcoinMarketPriceInformationRaw(
                status = null,
                name = null,
                unit = null,
                period = null,
                description = null,
                values = null))
    }

    @Test
    fun`Check that the EssentialParamMissingException is thrown when the mandatory params are wrong`() {

        // Setting up the Exception that we are expecting to get.
        thrownException.expect(EssentialParamMissingException::class.java)
        thrownException.expectMessage("status, name, unit, period, description, values")

        // Triggering the function that we want to test.
        mapper.apply(BitcoinMarketPriceInformationRaw(
                status = "ko",
                name = "",
                unit = "",
                period = "",
                description = "",
                values = emptyList()))
    }

    /**
     * Helper method used to instantiate a [BitcoinMarketPriceInformationRaw] object.
     */
    private fun instantiateBitcoinMarketPriceInformationRawObject() =
            BitcoinMarketPriceInformationRaw(
                    status = "ok",
                    name = "Market Price (USD)",
                    unit = "USD",
                    period = "day",
                    description = "Average USD market price across major bitcoin exchanges.",
                    values = listOf(BitcoinMarketPriceRaw(1L, 1f)))
}

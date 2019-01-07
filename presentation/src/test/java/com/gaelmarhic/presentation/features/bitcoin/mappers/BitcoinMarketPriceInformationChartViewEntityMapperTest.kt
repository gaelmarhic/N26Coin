package com.gaelmarhic.presentation.features.bitcoin.mappers

import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPrice
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class BitcoinMarketPriceInformationChartViewEntityMapperTest {

    /**
     * Instance of the class that we want to test.
     */
    private val mapper = BitcoinMarketPriceInformationChartViewEntityMapper()

    @Test
    fun `Test the mapping of the whole object`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                listOf(
                        BitcoinMarketPrice(1515283200, 16651.47f),
                        BitcoinMarketPrice(1515715200, 13912.88f)))

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.entryList[0].x, 1515283200f)
        assertEquals(viewEntity.entryList[0].y, 16651.47f)
        assertEquals(viewEntity.entryList[1].x, 1515715200f)
        assertEquals(viewEntity.entryList[1].y, 13912.88f)
        assertEquals(viewEntity.xAxisLabelCount, 2)
        assertEquals(viewEntity.description, "Average USD market price across major bitcoin exchanges.")
        assertEquals(viewEntity.currency, "USD")
    }

    @Test
    fun `Test the mapping of the xAxisLabelCount property - Case 1`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                emptyList())

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.xAxisLabelCount, 0)
    }

    @Test
    fun `Test the mapping of the xAxisLabelCount property - Case 2`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                listOf(BitcoinMarketPrice(0, 0f)))

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.xAxisLabelCount, 1)
    }

    @Test
    fun `Test the mapping of the xAxisLabelCount property - Case 3`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                MutableList(2) { BitcoinMarketPrice(0, 0f) })

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.xAxisLabelCount, 2)
    }

    @Test
    fun `Test the mapping of the xAxisLabelCount property - Case 4`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                MutableList(3) { BitcoinMarketPrice(0, 0f) })

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.xAxisLabelCount, 3)
    }

    @Test
    fun `Test the mapping of the xAxisLabelCount property - Case 5`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                MutableList(4) { BitcoinMarketPrice(0, 0f) })

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.xAxisLabelCount, 4)
    }

    @Test
    fun `Test the mapping of the xAxisLabelCount property - Case 6`() {

        val bitcoinMarketPriceInformation = instantiateBitcoinMarketPriceInformationObject(
                MutableList(5) { BitcoinMarketPrice(0, 0f) })

        val viewEntity = mapper.apply(bitcoinMarketPriceInformation)

        assertEquals(viewEntity.xAxisLabelCount, 4)
    }

    /**
     * Helper method used to instantiate a [BitcoinMarketPriceInformation] object.
     */
    private fun instantiateBitcoinMarketPriceInformationObject(values: List<BitcoinMarketPrice>) =
            BitcoinMarketPriceInformation(
                    status = "ok",
                    name = "Market Price (USD)",
                    unit = "USD",
                    period = "day",
                    description = "Average USD market price across major bitcoin exchanges.",
                    values = values)
}

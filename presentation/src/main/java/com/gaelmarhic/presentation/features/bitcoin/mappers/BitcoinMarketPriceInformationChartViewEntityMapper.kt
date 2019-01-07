package com.gaelmarhic.presentation.features.bitcoin.mappers

import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import com.github.mikephil.charting.data.Entry
import io.reactivex.functions.Function

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
class BitcoinMarketPriceInformationChartViewEntityMapper:
        Function<BitcoinMarketPriceInformation, BitcoinMarketPriceInformationChartViewEntity> {

    override fun apply(data: BitcoinMarketPriceInformation): BitcoinMarketPriceInformationChartViewEntity {

        val entryList = data.values.map { Entry(it.timestamp.toFloat(), it.amount) }
        val xAxisLabelCount = when (entryList.size) {
            0 -> 0  // Will not occur, since already checked.
            1 -> 1
            2 -> 2
            3 -> 3
            else -> 4
        }

        return BitcoinMarketPriceInformationChartViewEntity(
                entryList = entryList,
                xAxisLabelCount = xAxisLabelCount,
                currency = data.unit,
                description = data.description
        )
    }
}

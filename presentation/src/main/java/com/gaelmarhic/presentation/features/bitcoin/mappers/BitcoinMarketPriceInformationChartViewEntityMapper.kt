package com.gaelmarhic.presentation.features.bitcoin.mappers

import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import io.reactivex.functions.Function

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
class BitcoinMarketPriceInformationChartViewEntityMapper:
        Function<BitcoinMarketPriceInformation, BitcoinMarketPriceInformationChartViewEntity> {

    override fun apply(t: BitcoinMarketPriceInformation): BitcoinMarketPriceInformationChartViewEntity {
        // TODO: To be implemented.
        return BitcoinMarketPriceInformationChartViewEntity()
    }
}

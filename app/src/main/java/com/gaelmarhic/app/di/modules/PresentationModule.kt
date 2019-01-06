package com.gaelmarhic.app.di.modules

import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Module
class PresentationModule {

    @Provides
    @Singleton
    fun provideBitcoinMarketPriceInformationChartViewEntityMapper(): BitcoinMarketPriceInformationChartViewEntityMapper =
            BitcoinMarketPriceInformationChartViewEntityMapper()
}

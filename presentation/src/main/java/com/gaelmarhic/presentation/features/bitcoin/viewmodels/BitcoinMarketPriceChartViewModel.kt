package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.lifecycle.ViewModel
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
class BitcoinMarketPriceChartViewModel(
        private val retrieveUseCase: RetrieveBitcoinMarketPriceInformationUseCase,
        private val mapper: BitcoinMarketPriceInformationChartViewEntityMapper): ViewModel()

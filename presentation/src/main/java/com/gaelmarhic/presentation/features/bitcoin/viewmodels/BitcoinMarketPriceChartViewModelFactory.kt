package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper
import javax.inject.Inject

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Suppress("UNCHECKED_CAST")
class BitcoinMarketPriceChartViewModelFactory @Inject constructor(
        private val retrieveUseCase: RetrieveBitcoinMarketPriceInformationUseCase,
        private val mapper: BitcoinMarketPriceInformationChartViewEntityMapper):
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BitcoinMarketPriceChartViewModel() as T
    }
}

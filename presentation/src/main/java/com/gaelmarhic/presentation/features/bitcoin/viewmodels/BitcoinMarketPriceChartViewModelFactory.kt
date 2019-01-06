package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import javax.inject.Inject

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Suppress("UNCHECKED_CAST")
class BitcoinMarketPriceChartViewModelFactory @Inject constructor(
        private val retrieveUseCase: RetrieveBitcoinMarketPriceInformationUseCase):
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BitcoinMarketPriceChartViewModel() as T
    }
}

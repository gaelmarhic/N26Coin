package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Suppress("UNCHECKED_CAST")
class BitcoinMarketPriceChartViewModelFactory @Inject constructor(): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BitcoinMarketPriceChartViewModel() as T
    }
}

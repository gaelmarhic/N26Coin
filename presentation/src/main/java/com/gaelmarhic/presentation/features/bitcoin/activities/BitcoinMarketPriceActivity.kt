package com.gaelmarhic.presentation.features.bitcoin.activities

import com.gaelmarhic.presentation.R
import com.gaelmarhic.presentation.base.BaseInjectingActivity
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModelFactory
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class BitcoinMarketPriceActivity: BaseInjectingActivity() {

    /**
     * ViewModel factory that we will use to instantiate our [ViewModel].
     */
    @Inject
    lateinit var bitcoinMarketPriceChartViewModelFactory: BitcoinMarketPriceChartViewModelFactory

    override fun getLayoutId(): Int = R.layout.activity_bitcoin_market_price
}

package com.gaelmarhic.presentation.features.bitcoin.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.gaelmarhic.presentation.R
import com.gaelmarhic.presentation.base.BaseInjectingActivity
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModelFactory
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModel
import javax.inject.Inject

class BitcoinMarketPriceActivity: BaseInjectingActivity() {

    /**
     * ViewModel factory that we will use to instantiate our [ViewModel].
     */
    @Inject
    lateinit var bitcoinMarketPriceChartViewModelFactory: BitcoinMarketPriceChartViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModelLiveData()
    }

    override fun getLayoutId(): Int = R.layout.activity_bitcoin_market_price

    /**
     * Function that will set up the [ViewModel]'s [LiveData] observation.
     */
    private fun observeViewModelLiveData() {
        val viewModel = ViewModelProviders
                .of(this, bitcoinMarketPriceChartViewModelFactory)
                .get(BitcoinMarketPriceChartViewModel::class.java)

        viewModel.bitcoinMarketPriceInformationLiveData.observe(
                this, Observer { updateScreen(it) })
    }

    /**
     * Function in charge of painting the screen according to [BitcoinMarketPriceInformationChartViewEntity].
     */
    private fun updateScreen(viewEntity: BitcoinMarketPriceInformationChartViewEntity?) {
        // TODO: To be implemented.
    }
}

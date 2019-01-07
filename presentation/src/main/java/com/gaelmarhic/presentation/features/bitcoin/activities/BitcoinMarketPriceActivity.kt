package com.gaelmarhic.presentation.features.bitcoin.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.gaelmarhic.presentation.R
import com.gaelmarhic.presentation.base.BaseInjectingActivity
import com.gaelmarhic.presentation.common.constants.Constants.Companion.SHORT_DATE_FORMAT
import com.gaelmarhic.presentation.common.extensions.getFormattedDateFromTimestamp
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import com.gaelmarhic.presentation.features.bitcoin.uicomponents.BitcoinMarketPriceLineChartMarkerView
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModel
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModelFactory
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_bitcoin_market_price.*
import javax.inject.Inject

class BitcoinMarketPriceActivity: BaseInjectingActivity() {

    companion object {

        const val CHART_ANIMATION_DURATION_MS = 1500
    }

    /**
     * ViewModel factory that we will use to instantiate our [ViewModel].
     */
    @Inject
    lateinit var bitcoinMarketPriceChartViewModelFactory: BitcoinMarketPriceChartViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureChartEmptyView()
        configureBottomNavigationBar()
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
     * Function used to set up the Chart's Empty View.
     */
    private fun configureChartEmptyView() {

        // Empty view
        chart.setNoDataText(getString(R.string.bitcoin_market_price_activity_chart_placeholder_text))
        chart.setNoDataTextColor(ContextCompat.getColor(this, android.R.color.black))
    }

    /**
     * Function used to set up the Bottom Navigation Bar.
     */
    private fun configureBottomNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                // TODO: To be implemented.
            }
        }
    }

    /**
     * Function in charge of painting the screen according to [BitcoinMarketPriceInformationChartViewEntity].
     */
    private fun updateScreen(viewEntity: BitcoinMarketPriceInformationChartViewEntity?) {

        viewEntity?.let {

            // X axis
            val xAxis = chart.xAxis
            xAxis.position = BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setLabelCount(it.xAxisLabelCount, true)
            xAxis.setValueFormatter { value, _ ->
                value.toLong().getFormattedDateFromTimestamp(SHORT_DATE_FORMAT)
            }

            // Y axis
            chart.axisLeft.granularity = 1f
            chart.axisRight.isEnabled = false

            // Data set
            val dataSetElementsColor = ContextCompat.getColor(this, R.color.colorPrimary)
            val dataSet = LineDataSet(it.entryList, it.currency)
            dataSet.setDrawValues(false)
            dataSet.color = dataSetElementsColor
            dataSet.valueTextColor = dataSetElementsColor
            dataSet.highLightColor = dataSetElementsColor
            dataSet.setCircleColor(dataSetElementsColor)

            // Chart
            chart.setScaleEnabled(false)
            chart.description.text = it.description
            chart.data = LineData(dataSet)
            chart.marker = BitcoinMarketPriceLineChartMarkerView(it.currency, this)
            chart.animateX(CHART_ANIMATION_DURATION_MS)
        }
    }
}

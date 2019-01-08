package com.gaelmarhic.presentation.features.bitcoin.activities

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View.GONE
import android.view.View.VISIBLE
import com.gaelmarhic.presentation.R
import com.gaelmarhic.presentation.base.BaseInjectingActivity
import com.gaelmarhic.presentation.common.constants.Constants.Companion.SHORT_DATE_FORMAT
import com.gaelmarhic.presentation.common.extensions.getFormattedDateFromTimestamp
import com.gaelmarhic.presentation.common.extensions.toast
import com.gaelmarhic.presentation.common.streams.StreamState
import com.gaelmarhic.presentation.common.streams.StreamState.Getting
import com.gaelmarhic.presentation.common.streams.StreamState.Failed
import com.gaelmarhic.presentation.common.streams.StreamState.Retrieved
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import com.gaelmarhic.presentation.features.bitcoin.uicomponents.BitcoinMarketPriceLineChartMarkerView
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModel
import com.gaelmarhic.presentation.features.bitcoin.viewmodels.BitcoinMarketPriceChartViewModelFactory
import com.github.mikephil.charting.charts.Chart.PAINT_INFO
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
                this, Observer { handleViewModelLiveDataEvents(it) })
    }

    /**
     * Function used to set up the Chart's Empty View.
     */
    private fun configureChartEmptyView() {

        // Empty view
        chart.setNoDataText(getString(R.string.bitcoin_market_price_activity_chart_placeholder_text))
        chart.setNoDataTextColor(ContextCompat.getColor(this, android.R.color.black))
        val noDataTextSize = resources.getDimensionPixelSize(R.dimen.bitcoin_market_price_activity_chart_no_data_text_size).toFloat()
        chart.getPaint(PAINT_INFO).textSize = noDataTextSize
    }

    /**
     * Function used to set up the Bottom Navigation Bar.
     */
    private fun configureBottomNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val text = getString(R.string.feature_not_implemented_yet)
            when(it.itemId) {
                R.id.bottom_navigation_bar_money_icon -> {
                    toast(text)
                    true
                }
                R.id.bottom_navigation_bar_card_icon -> {
                    toast(text)
                    true
                }
                R.id.bottom_navigation_bar_plus_icon -> {
                    toast(text)
                    true
                }
                R.id.bottom_navigation_bar_notification_icon -> {
                    toast(text)
                    true
                }
                R.id.bottom_navigation_bar_user_icon -> {
                    toast(text)
                    true
                }
                else -> { false }
            }
        }
    }

    /**
     * Function that is in charge of handling the events from the ViewModel's Livedata.
     *
     * @param streamState The current state of the stream.
     */
    private fun handleViewModelLiveDataEvents(streamState: StreamState?) {

        when(streamState) {
            is Getting -> {
                progressBar.visibility = VISIBLE
            }
            is Retrieved -> {
                progressBar.visibility = GONE
                updateScreen(streamState.content as? BitcoinMarketPriceInformationChartViewEntity)
            }
            is Failed -> {
                // TODO: If I had had more time, I would have implemented a retry mechanism.
                // TODO: Since I have not had enough time, it is just a toast :)
                progressBar.visibility = GONE
                toast(getString(R.string.fetching_error_message))
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

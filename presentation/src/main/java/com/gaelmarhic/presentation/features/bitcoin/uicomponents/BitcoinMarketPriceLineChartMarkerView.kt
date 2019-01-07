package com.gaelmarhic.presentation.features.bitcoin.uicomponents

import android.annotation.SuppressLint
import android.content.Context
import com.gaelmarhic.presentation.R
import com.gaelmarhic.presentation.common.constants.Constants.Companion.LONG_DATE_FORMAT
import com.gaelmarhic.presentation.common.constants.Constants.Companion.THOUSANDS_SEPARATOR
import com.gaelmarhic.presentation.common.extensions.applyThousandsSeparator
import com.gaelmarhic.presentation.common.extensions.getFormattedDateFromTimestamp
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.bitcoin_market_price_linechart_markerview.view.*

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
@SuppressLint("ViewConstructor")
class BitcoinMarketPriceLineChartMarkerView(private val currency: String,
                                            context: Context):
        MarkerView(context, R.layout.bitcoin_market_price_linechart_markerview) {

    /**
     * Offset used to position the [MarkerView] on the graph.
     */
    private var customOffset: MPPointF? = null

    override fun refreshContent(entry: Entry, highlight: Highlight) {

        val formattedDate = entry.x.toLong().getFormattedDateFromTimestamp(LONG_DATE_FORMAT)
        val formattedAmount = entry.y.toLong().applyThousandsSeparator(THOUSANDS_SEPARATOR)
        val amountWithCurrency = "$currency: $formattedAmount"

        topTextView.text = formattedDate
        bottomTextView.text = amountWithCurrency

        super.refreshContent(entry, highlight)
    }

    override fun getOffset(): MPPointF {

        if (customOffset == null) {
            customOffset = MPPointF(-(width / 2).toFloat(), - height.toFloat())
        }

        return customOffset as MPPointF
    }
}

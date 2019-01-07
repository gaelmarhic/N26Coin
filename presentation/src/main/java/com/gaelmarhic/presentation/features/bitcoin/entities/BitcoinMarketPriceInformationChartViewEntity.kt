package com.gaelmarhic.presentation.features.bitcoin.entities

import com.github.mikephil.charting.data.Entry

/**
 * Created by Gaël Marhic on 06/01/2019.
 *
 * @property entryList The list of entries to be displayed in the graph.
 * @property xAxisLabelCount The quantity of labels to display on the X axis.
 * @property currency The bitcoin price's currency.
 * @property description The description of the graph.
 */
class BitcoinMarketPriceInformationChartViewEntity(
        val entryList: List<Entry>,
        val xAxisLabelCount: Int,
        val currency: String,
        val description: String
)

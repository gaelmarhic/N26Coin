package com.gaelmarhic.data.bitcoin.entities

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * @property status The request's status provided by the API.
 * @property name The name of our current request.
 * @property unit The currency in which the market price is provided.
 * @property period The period between two market price entries.
 * @property description The description of our current request.
 * @property values The list of market prices over time.
 */
class BitcoinMarketPriceInformation(
        val status: String,
        val name: String,
        val unit: String,
        val period: String,
        val description: String,
        val values: List<BitcoinMarketPrice>
)

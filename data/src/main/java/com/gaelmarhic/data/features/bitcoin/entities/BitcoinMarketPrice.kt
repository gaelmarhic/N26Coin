package com.gaelmarhic.data.features.bitcoin.entities

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * @property timestamp The exact timestamp corresponding to this [BitcoinMarketPrice] entry.
 * @property amount The bitcoin's value corresponding to the given [timestamp].
 */
data class BitcoinMarketPrice(
        private val timestamp: Long,
        private val amount: Float
)

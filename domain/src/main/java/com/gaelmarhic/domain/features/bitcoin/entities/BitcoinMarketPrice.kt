package com.gaelmarhic.domain.features.bitcoin.entities

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * @property timestamp The exact timestamp corresponding to this [BitcoinMarketPrice] entry.
 * @property amount The bitcoin's value corresponding to the given [timestamp].
 */
data class BitcoinMarketPrice(
        val timestamp: Long,
        val amount: Float
)

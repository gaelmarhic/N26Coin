package com.gaelmarhic.data.features.bitcoin.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * @property timestamp The exact timestamp corresponding to this [BitcoinMarketPriceRaw] entry.
 * @property amount The bitcoin's value corresponding to the given [timestamp].
 */
data class BitcoinMarketPriceRaw(

        @SerializedName("x")
        val timestamp: Long?,

        @SerializedName("y")
        val amount: Float?
)

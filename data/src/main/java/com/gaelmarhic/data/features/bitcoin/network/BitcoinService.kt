package com.gaelmarhic.data.features.bitcoin.network

import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceInformationRaw
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
interface BitcoinService {

    @GET("charts/market-price")
    fun fetchBitcoinMarketPrice(@Query("timespan") timeSpan: String? = null,
                                @Query("start") start: String? = null):
            Single<BitcoinMarketPriceInformationRaw>
}

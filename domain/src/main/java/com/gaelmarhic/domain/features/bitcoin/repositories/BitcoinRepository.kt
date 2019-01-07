package com.gaelmarhic.domain.features.bitcoin.repositories

import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import io.reactivex.Completable
import io.reactivex.Observable
import polanski.option.Option

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * This interface corresponds to the definition of the [BitcoinRepository]. Its implementation
 * can be found in the data module. It has been done this way in order to respect the Dependency
 * Inversion principle from SOLID.
 */
interface BitcoinRepository {

    /**
     * Function used to fetch the Bitcoin's market price information from the Blockchain's API.
     *
     * @param timeSpan Duration of the chart.
     * @return Returns a [Completable].
     */
    fun fetchBitcoinMarketPriceInformation(timeSpan: String): Completable

    /**
     * Function used to get the stream of the Bitcoin's market price information.
     *
     * @return Returns an [Observable].
     */
    fun getBitcoinMarketPriceInformation(): Observable<Option<BitcoinMarketPriceInformation>>
}

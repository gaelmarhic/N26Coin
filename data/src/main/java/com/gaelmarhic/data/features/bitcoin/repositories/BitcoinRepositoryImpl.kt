package com.gaelmarhic.data.features.bitcoin.repositories

import com.gaelmarhic.data.common.store.memory.MemoryReactiveStore
import com.gaelmarhic.data.features.bitcoin.mappers.BitcoinMarketPriceInformationMapper
import com.gaelmarhic.data.features.bitcoin.network.BitcoinService
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.domain.features.bitcoin.repositories.BitcoinRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * This class corresponds to the implementation of the [BitcoinRepository]. Its definition can be
 * found in the domain module. It has been done this way in order to respect the Dependency
 * Inversion principle from SOLID.
 */
class BitcoinRepositoryImpl(private val store: MemoryReactiveStore<BitcoinMarketPriceInformation>,
                            private val bitcoinService: BitcoinService,
                            private val mapper: BitcoinMarketPriceInformationMapper): BitcoinRepository {

    /**
     * Function used to fetch the Bitcoin's market price information from the Blockchain's API.
     *
     * @return Returns a [Completable].
     */
    override fun fetchBitcoinMarketPriceInformation(): Completable =
            bitcoinService.fetchBitcoinMarketPrice()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .map(mapper)
                    .doOnSuccess(store::replace)
                    .ignoreElement()

    /**
     * Function used to get the stream of the Bitcoin's market price information.
     *
     * @return Returns an [Observable].
     */
    override fun getBitcoinMarketPriceInformation() = store.get()
}

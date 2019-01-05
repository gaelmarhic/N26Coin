package com.gaelmarhic.di.domain.features.bitcoin

import com.gaelmarhic.data.common.store.ReactiveStore
import com.gaelmarhic.data.features.bitcoin.mappers.BitcoinMarketPriceInformationMapper
import com.gaelmarhic.data.features.bitcoin.network.BitcoinService
import com.gaelmarhic.data.features.bitcoin.repositories.BitcoinRepositoryImpl
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.domain.features.bitcoin.repositories.BitcoinRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Module
class BitcoinDomainModule {

    @Provides
    @Singleton
    fun provideBitcoinRepository(store: ReactiveStore<BitcoinMarketPriceInformation>,
                                 bitcoinService: BitcoinService,
                                 mapper: BitcoinMarketPriceInformationMapper): BitcoinRepository =
            BitcoinRepositoryImpl(store, bitcoinService, mapper)
}

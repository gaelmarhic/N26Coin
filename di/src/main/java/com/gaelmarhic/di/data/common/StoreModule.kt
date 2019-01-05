package com.gaelmarhic.di.data.common

import com.gaelmarhic.data.common.constants.Constants.Companion.MEMORY_MAX_AGE
import com.gaelmarhic.data.common.providers.TimestampProvider
import com.gaelmarhic.data.common.store.ReactiveStore
import com.gaelmarhic.data.common.store.Store.MemoryStore
import com.gaelmarhic.data.common.store.memory.Memory
import com.gaelmarhic.data.common.store.memory.MemoryReactiveStore
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Module
class StoreModule {

    @Provides
    @Singleton
    fun provideMemoryStore(timestampProvider: TimestampProvider):
            MemoryStore<BitcoinMarketPriceInformation> {
        return Memory(timestampProvider, MEMORY_MAX_AGE)
    }

    @Provides
    @Singleton
    fun provideReactiveMemoryStore(store: MemoryStore<BitcoinMarketPriceInformation>):
            ReactiveStore<BitcoinMarketPriceInformation> {
        return MemoryReactiveStore(store)
    }
}

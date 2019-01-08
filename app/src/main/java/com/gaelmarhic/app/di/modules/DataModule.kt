package com.gaelmarhic.app.di.modules

import com.gaelmarhic.app.annotations.OpenForTesting
import com.gaelmarhic.app.extensions.enableLogs
import com.gaelmarhic.app.extensions.setFailureHandler
import com.gaelmarhic.data.common.constants.Constants
import com.gaelmarhic.data.common.constants.Constants.Companion.MEMORY_MAX_AGE
import com.gaelmarhic.data.common.providers.TimestampProvider
import com.gaelmarhic.data.common.store.memory.Memory
import com.gaelmarhic.data.common.store.memory.MemoryReactiveStore
import com.gaelmarhic.data.features.bitcoin.mappers.BitcoinMarketPriceInformationMapper
import com.gaelmarhic.data.features.bitcoin.network.BitcoinService
import com.gaelmarhic.data.features.bitcoin.repositories.BitcoinRepositoryImpl
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.domain.features.bitcoin.repositories.BitcoinRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@OpenForTesting
@Module
class DataModule {

    companion object {

        private const val BLOCKCHAIN_BASE_URL = "BLOCKCHAIN_BASE_URL"
        private const val DEFAULT_CONNECT_TIMEOUT = 30L
        private const val CONNECTION_ATTEMPTS = 3
    }

    @Provides
    @Named(BLOCKCHAIN_BASE_URL)
    fun provideBlockchainBaseUrl() = Constants.BLOCKCHAIN_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        enableLogs()
        setFailureHandler(DEFAULT_CONNECT_TIMEOUT, CONNECTION_ATTEMPTS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(@Named(BLOCKCHAIN_BASE_URL) baseUrl: String,
                        okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()

    @Provides
    @Singleton
    fun provideBitcoinService(retrofit: Retrofit): BitcoinService =
            retrofit.create(BitcoinService::class.java)

    @Provides
    @Singleton
    fun provideMemory(timestampProvider: TimestampProvider): Memory<BitcoinMarketPriceInformation> =
            Memory(timestampProvider, MEMORY_MAX_AGE)

    @Provides
    @Singleton
    fun provideMemoryReactiveStore(memory: Memory<BitcoinMarketPriceInformation>):
            MemoryReactiveStore<BitcoinMarketPriceInformation> = MemoryReactiveStore(memory)

    @Provides
    @Singleton
    fun provideBitcoinMarketPriceInformationMapper(): BitcoinMarketPriceInformationMapper =
            BitcoinMarketPriceInformationMapper()

    @Provides
    @Singleton
    fun provideBitcoinRepository(reactiveStore: MemoryReactiveStore<BitcoinMarketPriceInformation>,
                                 service: BitcoinService,
                                 mapper: BitcoinMarketPriceInformationMapper): BitcoinRepository =
            BitcoinRepositoryImpl(reactiveStore, service, mapper)
}

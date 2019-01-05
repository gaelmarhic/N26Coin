package com.gaelmarhic.di.data.features.bitcoin

import com.gaelmarhic.data.features.bitcoin.network.BitcoinService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Module
class BitcoinDataModule {

    @Provides
    @Singleton
    fun provideBitcoinService(retrofit: Retrofit): BitcoinService =
            retrofit.create(BitcoinService::class.java)
}

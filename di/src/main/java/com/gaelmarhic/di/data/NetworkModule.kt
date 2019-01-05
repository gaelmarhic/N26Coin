package com.gaelmarhic.di.data

import com.gaelmarhic.data.bitcoin.constants.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Module
class NetworkModule {

    companion object {

        private const val BLOCKCHAIN_BASE_URL = "BLOCKCHAIN_BASE_URL"
    }

    @Provides
    @Named(BLOCKCHAIN_BASE_URL)
    fun provideBlockchainBaseUrl() = Constants.BLOCKCHAIN_BASE_URL
}

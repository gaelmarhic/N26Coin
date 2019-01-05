package com.gaelmarhic.di.data.common

import com.gaelmarhic.data.common.providers.TimestampProvider
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
class ProviderModule {

    @Provides
    @Singleton
    fun provideTimestampProvider() = TimestampProvider()
}

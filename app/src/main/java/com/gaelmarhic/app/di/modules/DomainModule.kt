package com.gaelmarhic.app.di.modules

import com.gaelmarhic.domain.features.bitcoin.repositories.BitcoinRepository
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideRetrieveBitcoinMarketPriceInformationUseCase(repository: BitcoinRepository)
         = RetrieveBitcoinMarketPriceInformationUseCase(repository)
}

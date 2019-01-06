package com.gaelmarhic.presentation.di.activities

import com.gaelmarhic.presentation.features.bitcoin.activities.BitcoinMarketPriceActivity
import com.gaelmarhic.presentation.features.bitcoin.di.BitcoinPresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Module
abstract class ActivityBindings {

    @ContributesAndroidInjector(modules = [BitcoinPresentationModule::class])
    abstract fun bindBitcoinMarketPriceActivity(): BitcoinMarketPriceActivity
}

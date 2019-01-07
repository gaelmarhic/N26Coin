package com.gaelmarhic.app.di.activities

import com.gaelmarhic.presentation.features.bitcoin.activities.BitcoinMarketPriceActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Module
abstract class ActivityBindings {

    @ContributesAndroidInjector
    abstract fun bindBitcoinMarketPriceActivity(): BitcoinMarketPriceActivity
}

package com.gaelmarhic.di.data.common

import com.gaelmarhic.di.data.features.bitcoin.BitcoinDataModule
import dagger.Subcomponent

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Subcomponent(modules = [(NetworkModule::class), (BitcoinDataModule::class), (ProviderModule::class)])
interface DataComponent

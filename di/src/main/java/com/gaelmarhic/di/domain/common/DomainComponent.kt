package com.gaelmarhic.di.domain.common

import com.gaelmarhic.di.domain.features.bitcoin.BitcoinDomainModule
import dagger.Subcomponent

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Subcomponent(modules = [(BitcoinDomainModule::class)])
interface DomainComponent

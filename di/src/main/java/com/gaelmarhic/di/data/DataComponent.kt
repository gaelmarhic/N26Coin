package com.gaelmarhic.di.data

import dagger.Subcomponent

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Subcomponent(modules = [(NetworkModule::class)])
interface DataComponent

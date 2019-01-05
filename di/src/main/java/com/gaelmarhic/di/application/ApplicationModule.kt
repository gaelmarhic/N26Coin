package com.gaelmarhic.di.application

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Module
class ApplicationModule {

    @Provides
    fun provideApplicationContext(application: Application): Context =
            application.applicationContext
}

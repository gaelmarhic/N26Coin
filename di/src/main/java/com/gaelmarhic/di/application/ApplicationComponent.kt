package com.gaelmarhic.di.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Singleton
@Component
interface ApplicationComponent {

    fun inject(application: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}

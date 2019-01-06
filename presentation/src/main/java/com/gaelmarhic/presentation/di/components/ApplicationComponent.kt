package com.gaelmarhic.presentation.di.components

import android.app.Application
import com.gaelmarhic.presentation.application.N26CoinApplication
import com.gaelmarhic.presentation.di.activities.ActivityBindings
import com.gaelmarhic.data.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBindings::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(app: N26CoinApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): ApplicationComponent
    }
}

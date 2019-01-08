package com.gaelmarhic.app.di.components

import android.app.Application
import com.gaelmarhic.app.application.N26CoinApplication
import com.gaelmarhic.app.di.activities.ActivityBindings
import com.gaelmarhic.app.di.modules.DataModule
import com.gaelmarhic.app.di.modules.DomainModule
import com.gaelmarhic.app.di.modules.PresentationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBindings::class,
    PresentationModule::class,
    DomainModule::class,
    DataModule::class])
interface ApplicationComponent {

    fun inject(app: N26CoinApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun dataModule(dataModule: DataModule): Builder
        fun build(): ApplicationComponent
    }
}

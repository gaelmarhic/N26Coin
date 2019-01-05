package com.gaelmarhic.di.application

import android.app.Application
import com.gaelmarhic.di.data.DataComponent
import com.gaelmarhic.di.domain.DomainComponent
import com.gaelmarhic.di.presentation.PresentationComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(application: Application)
    fun addPresentationComponent(): PresentationComponent
    fun addDomainComponent(): DomainComponent
    fun addDataComponent(): DataComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}

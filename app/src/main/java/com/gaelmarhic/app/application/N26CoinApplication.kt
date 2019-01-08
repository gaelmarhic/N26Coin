package com.gaelmarhic.app.application

import android.app.Activity
import android.app.Application
import com.gaelmarhic.app.annotations.OpenForTesting
import com.gaelmarhic.app.di.components.ApplicationComponent
import com.gaelmarhic.app.di.components.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
@OpenForTesting
class N26CoinApplication: Application(), HasActivityInjector {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector() = activityDispatchingAndroidInjector
}

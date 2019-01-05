package com.gaelmarhic.n26coin.application

import android.app.Application
import com.gaelmarhic.di.application.ApplicationComponent
import com.gaelmarhic.di.application.DaggerApplicationComponent

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
class N26CoinApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}

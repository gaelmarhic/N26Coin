package com.gaelmarhic.app.testutils.application

import com.gaelmarhic.app.application.N26CoinApplication
import com.gaelmarhic.app.di.components.ApplicationComponent
import com.gaelmarhic.app.di.components.DaggerApplicationComponent
import com.gaelmarhic.app.testutils.di.TestDataModule

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class N26CoinTestApplication: N26CoinApplication() {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .application(this)
                .dataModule(TestDataModule())
                .build()
    }
}

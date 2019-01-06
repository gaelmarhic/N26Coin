package com.gaelmarhic.presentation.base

import android.os.Bundle
import dagger.android.AndroidInjection

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
abstract class BaseInjectingActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}

package com.gaelmarhic.presentation.base

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Gaël Marhic on 06/01/2019.
 *
 * Useful class used to provide common behaviours to all of the project's activities.
 * IMPORTANT: Also, this way we make sure that all of our activities implement the [LifecycleOwner]
 * interface.
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    /**
     * Method used to get the children's layout id.
     *
     * @return Returns the layout id.
     */
    protected abstract fun getLayoutId(): Int
}

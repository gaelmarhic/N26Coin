package com.gaelmarhic.data.common.providers

import javax.inject.Inject

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
class TimestampProvider @Inject constructor() {

    /**
     * The current time in milliseconds.
     */
    val currentTimeMillis: Long
    get() = System.currentTimeMillis()
}

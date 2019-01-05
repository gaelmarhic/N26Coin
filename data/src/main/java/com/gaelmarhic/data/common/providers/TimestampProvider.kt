package com.gaelmarhic.data.common.providers

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
class TimestampProvider() {

    /**
     * The current time in milliseconds.
     */
    val currentTimeMillis: Long
    get() = System.currentTimeMillis()
}

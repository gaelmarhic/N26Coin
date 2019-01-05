package com.gaelmarhic.data.common.store

import io.reactivex.Observable
import polanski.option.Option

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * Simplified version of the [ReactiveStore] provided in https://github.com/n26/N26AndroidSamples.
 */
interface ReactiveStore<Value> {

    /**
     * Function used to store a value in the [ReactiveStore].
     *
     * @param value The value to be stored.
     */
    fun store(value: Value)

    /**
     * Function used to replace the value already stored in the [ReactiveStore].
     *
     * @param value The value to be stored.
     */
    fun replace(value: Value)

    /**
     * Function used to get the value stored in the [ReactiveStore].
     *
     * @return Returns an [Observable] that will be triggered in case changes occur in the store.
     */
    fun get(): Observable<Option<Value>>
}

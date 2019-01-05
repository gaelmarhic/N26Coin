package com.gaelmarhic.data.common.store

import io.reactivex.Maybe

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * Simplified version of the [Store] provided in https://github.com/n26/N26AndroidSamples.
 */
interface Store<Value> {

    /**
     * Function used to store a value in the [Store].
     *
     * @param value The value to be stored.
     */
    fun put(value: Value)

    /**
     * Function used to clear the [Store].
     */
    fun clear()

    /**
     * Function used to get the value stored in the [Store].
     *
     * @return Returns a [Maybe] with the [Value] wrapped inside.
     */
    fun get(): Maybe<Value>

    /**
     * Interface that should be used to implement a memory based store.
     */
    interface MemoryStore<Value>: Store<Value>

    /**
     * Interface that should be used to implement a disk based store.
     */
    interface DiskStore<Value>: Store<Value>
}

package com.gaelmarhic.data.common.store.memory

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * @property cachedObject The object to be cached in the device's memory.
 * @property creationTimestamp The timestamp corresponding to the creation of this [MemoryEntry].
 */
data class MemoryEntry<Object>(val cachedObject: Object, val creationTimestamp: Long)

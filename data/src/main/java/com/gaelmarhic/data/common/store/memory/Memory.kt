package com.gaelmarhic.data.common.store.memory

import com.gaelmarhic.data.common.providers.TimestampProvider
import com.gaelmarhic.data.common.store.Store
import com.gaelmarhic.data.common.store.Store.MemoryStore
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import polanski.option.Option.ofObj
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * Simplified version of the Cache class provided in https://github.com/n26/N26AndroidSamples.
 */
class Memory<Value>(private val timestampProvider: TimestampProvider,
                    private val timeOut: Long): MemoryStore<Value> {

    companion object {

        const val UNIQUE_VALUE_KEY = "Unique value key"
    }

    /**
     * Lifespan used to determine whether the cache has expired or not.
     */
    private val cacheLifespan: Option<Long>
    get() = ofObj(timeOut)

    /**
     * [HashMap] where the value will be stored.
     * IMPORTANT: We use a [ConcurrentHashMap], even though for this sample we will only store a
     * unique [MemoryEntry], so that we can hold our [MemoryEntry] and manage concurrency more
     * easily.
     */
    private val cache: ConcurrentHashMap<String, MemoryEntry<Value>> = ConcurrentHashMap()

    /**
     * Function used to store a value in the [Store].
     *
     * @param value The value to be stored.
     */
    override fun put(value: Value) {
        cache[UNIQUE_VALUE_KEY] = createMemoryEntry(value)
    }

    /**
     * Function used to get the value stored in the [Store].
     *
     * @return Returns a [Maybe] with the [Value] wrapped inside.
     */
    override fun get(): Maybe<Value> = Maybe.fromCallable { cache.containsKey(UNIQUE_VALUE_KEY) }
                .filter { isPresent -> isPresent }
                .map { cache[UNIQUE_VALUE_KEY] }
                .filter { this.isValid(it) }
                .map { it.cachedObject }
                .subscribeOn(Schedulers.computation())

    /**
     * Function used to clear the [Store].
     */
    override fun clear() {
        cache.clear()
    }

    /**
     * Function used to instantiate a new [MemoryEntry].
     *
     * @param value The value to be stored.
     * @return Returns the recently instantiated [MemoryEntry].
     */
    private fun createMemoryEntry(value: Value): MemoryEntry<Value> =
            MemoryEntry(value, timestampProvider.currentTimeMillis)

    /**
     * Function used to determine whether the cache is still valid.
     *
     * @return Returns true is the cache is still valid. Returns false otherwise.
     */
    private fun isValid(memoryEntry: MemoryEntry<Value>): Boolean {
        return cacheLifespan.match({ timeOut ->
            memoryEntry.creationTimestamp + timeOut > timestampProvider.currentTimeMillis
        }, { true })
    }
}

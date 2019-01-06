package com.gaelmarhic.data.common.store.memory

import com.gaelmarhic.data.common.store.ReactiveStore
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import polanski.option.Option
import polanski.option.Option.none
import polanski.option.Option.ofObj

/**
 * Created by Gaël Marhic on 05/01/2019.
 *
 * Simplified version of the [MemoryReactiveStore] provided in https://github.com/n26/N26AndroidSamples.
 */
class MemoryReactiveStore<Value>(private val store: Memory<Value>): ReactiveStore<Value> {

    /**
     * [Subject] used to propagate the [store]'s value updates.
     */
    private val subject: Subject<Option<Value>> by lazy {
        PublishSubject.create<Option<Value>>().toSerialized()
    }

    /**
     * Function used to store a value in the [ReactiveStore].
     *
     * @param value The value to be stored.
     */
    override fun store(value: Value) {
        store.put(value)
        subject.onNext(Option.ofObj(value))
    }

    /**
     * Function used to replace the value already stored in the [ReactiveStore].
     *
     * @param value The value to be stored.
     */
    override fun replace(value: Value) {
        store.clear()
        store(value)
    }

    /**
     * Function used to get the value stored in the [ReactiveStore].
     *
     * @return Returns an [Observable] that will be triggered in case changes occur in the store.
     */
    override fun get(): Observable<Option<Value>> =
            Observable.defer { subject.startWith(store.get().map { ofObj(it) }.blockingGet(none())) }
                    .observeOn(Schedulers.computation())
}

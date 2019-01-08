package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import com.gaelmarhic.presentation.common.streams.StreamState
import com.gaelmarhic.presentation.common.streams.StreamState.Getting
import com.gaelmarhic.presentation.common.streams.StreamState.Retrieved
import com.gaelmarhic.presentation.common.streams.StreamState.Failed
import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import polanski.option.Option.none

/**
 * Created by Gaël Marhic on 06/01/2019.
 */
class BitcoinMarketPriceChartViewModel(
        private val retrieveUseCase: RetrieveBitcoinMarketPriceInformationUseCase,
        private val mapper: BitcoinMarketPriceInformationChartViewEntityMapper): ViewModel() {

    /**
     * [LiveData] that will notify the UI when any change occurs in the data set.
     */
    val bitcoinMarketPriceInformationLiveData = MutableLiveData<StreamState>()

    /**
     * [CompositeDisposable] that will be used to manage the downcoming streams.
     */
    private val compositeDisposable = CompositeDisposable()

    /**
     * When this ViewModel is instantiated, we bind the downcoming streams to the CompositeDisposable.
     */
    init {
        compositeDisposable.add(bindToBitcoinMarketPriceInformation())
    }

    /**
     * Function in charge of binding the downcoming streams to the [CompositeDisposable].
     *
     * @return Returns a [Disposable].
     */
    private fun bindToBitcoinMarketPriceInformation(): Disposable {
        bitcoinMarketPriceInformationLiveData.postValue(Getting)
        return retrieveUseCase.getBehaviorStream(none())
                .observeOn(Schedulers.computation())
                .map(mapper)
                .subscribe({
                    bitcoinMarketPriceInformationLiveData.postValue((Retrieved(it)))
                }) {
                    bitcoinMarketPriceInformationLiveData.postValue(Failed)
                }
    }

    /**
     * When this [ViewModel] is cleared, we release the downcoming streams.
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

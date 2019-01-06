package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper
import io.reactivex.disposables.CompositeDisposable
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
    val bitcoinMarketPriceInformationLiveData = MutableLiveData<BitcoinMarketPriceInformationChartViewEntity>()

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
     */
    private fun bindToBitcoinMarketPriceInformation() =
            retrieveUseCase.getBehaviorStream(none())
                .observeOn(Schedulers.computation())
                .map(mapper)
                .subscribe(bitcoinMarketPriceInformationLiveData::postValue) {
                    // TODO: Error handling. To be implemented.
                }

    /**
     * When this [ViewModel] is cleared, we release the downcoming streams.
     */
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

package com.gaelmarhic.domain.features.bitcoin.usecases

import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.domain.features.bitcoin.repositories.BitcoinRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.Single.just
import n26.interactors.ReactiveInteractor.RetrieveInteractor
import n26.rx.UnwrapOptionTransformer
import polanski.option.Option

/**
 * Created by Gaël Marhic on 05/01/2019.
 */
class RetrieveBitcoinMarketPriceInformationUseCase(private val bitcoinRepository: BitcoinRepository):
        RetrieveInteractor<Void, BitcoinMarketPriceInformation> {

    companion object {

        /**
         * Duration of the chart that we want for this sample.
         */
        const val TIME_SPAN = "30days"
    }

    /**
     * Function that returns an [Observable] that will emit updates of the Bitcoin market price
     * information.
     *
     * @return Returns an [Observable].
     */
    override fun getBehaviorStream(params: Option<Void>): Observable<BitcoinMarketPriceInformation> =
            bitcoinRepository.getBitcoinMarketPriceInformation()
                    .flatMapSingle(this::fetchWhenNone)
                    .compose(UnwrapOptionTransformer.create())

    /**
     * Function that will fetch the Bitcoin market price information in case there is no data in
     * the store.
     *
     * @return Returns a [Single].
     */
    private fun fetchWhenNone(marketPriceInformation: Option<BitcoinMarketPriceInformation>) =
            if (marketPriceInformation.isNone) bitcoinRepository.fetchBitcoinMarketPriceInformation(TIME_SPAN)
            else { Completable.complete() }.andThen(just(marketPriceInformation))
}

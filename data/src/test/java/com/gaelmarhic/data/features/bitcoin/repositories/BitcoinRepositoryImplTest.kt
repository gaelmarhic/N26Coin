package com.gaelmarhic.data.features.bitcoin.repositories

import com.gaelmarhic.data.common.store.memory.MemoryReactiveStore
import com.gaelmarhic.data.features.bitcoin.entities.BitcoinMarketPriceInformationRaw
import com.gaelmarhic.data.features.bitcoin.mappers.BitcoinMarketPriceInformationMapper
import com.gaelmarhic.data.features.bitcoin.network.BitcoinService
import com.gaelmarhic.data.testutils.RxSchedulerOverrideRule
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import polanski.option.Option

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class BitcoinRepositoryImplTest {

    /**
     * Rule that swaps the background executors used by RxJava2 with a different one which executes
     * each task synchronously.
     */
    @get:Rule
    val rxJavaTestRule = RxSchedulerOverrideRule()

    /**
     * Mocked [MemoryReactiveStore] that we will use to test [BitcoinRepositoryImpl].
     */
    @MockK
    private lateinit var mockedMemoryReactiveStore: MemoryReactiveStore<BitcoinMarketPriceInformation>

    /**
     * Mocked [BitcoinService] that we will use to test [BitcoinRepositoryImpl].
     */
    @MockK
    private lateinit var mockedBitcoinService: BitcoinService

    /**
     * Mocked [BitcoinMarketPriceInformationMapper] that we will use to test [BitcoinRepositoryImpl].
     */
    @MockK
    private lateinit var mockedMapper: BitcoinMarketPriceInformationMapper

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var bitcoinRepositoryImpl: BitcoinRepositoryImpl

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the @MockK annotated variables.
        MockKAnnotations.init(this, relaxUnitFun = true)

        // Instantiating the class that we want to test.
        bitcoinRepositoryImpl = BitcoinRepositoryImpl(
                mockedMemoryReactiveStore,
                mockedBitcoinService,
                mockedMapper)
    }

    @Test
    fun `Check that the getBitcoinMarketPriceInformation function returns the Observable from the Reactive Store`() {

        // Mocking what needs to be mocked
        val storeObservable = Observable.empty<Option<BitcoinMarketPriceInformation>>()
        every { mockedMemoryReactiveStore.get() } returns storeObservable

        // Assertion
        assertEquals(bitcoinRepositoryImpl.getBitcoinMarketPriceInformation(), storeObservable)
    }

    @Test
    fun `Check that the fetchBitcoinMarketPriceInformation function emits an error when there is a network issue`() {

        // Mocking what needs to be mocked.
        val throwable = mockk<Throwable>()
        every { mockedBitcoinService.fetchBitcoinMarketPrice(any(), any()) } returns Single.error(throwable)

        // Assertion
        bitcoinRepositoryImpl.fetchBitcoinMarketPriceInformation("").test().assertError(throwable)
    }

    @Test
    fun `Check that the raw object that we got from the network is mapped `() {

        // Mocking what needs to be mocked.
        val rawBitcoinMarketPriceInformation = BitcoinMarketPriceInformationRaw(
                status = "ok",
                name = "name",
                unit = "unit",
                period = "period",
                description = "description",
                values = emptyList())
        val mockedBitcoinMarketPriceInformation = mockk<BitcoinMarketPriceInformation>()
        every { mockedBitcoinService.fetchBitcoinMarketPrice(any(), any()) } returns Single.just(rawBitcoinMarketPriceInformation)
        every { mockedMapper.apply(any()) } returns mockedBitcoinMarketPriceInformation

        // Triggering the function that we want to try
        bitcoinRepositoryImpl.fetchBitcoinMarketPriceInformation("").subscribe()

        // Assertion
        verify(exactly = 1) { mockedMapper.apply(any()) }
    }

    @Test
    fun `Check that the replace function from the Reactive Store is correctly triggered`() {

        // Mocking what needs to be mocked.
        val mockedBitcoinMarketPriceInformationRaw = mockk<BitcoinMarketPriceInformationRaw>()
        val mockedBitcoinMarketPriceInformation = mockk<BitcoinMarketPriceInformation>()
        every { mockedBitcoinService.fetchBitcoinMarketPrice(any(), any()) } returns Single.just(mockedBitcoinMarketPriceInformationRaw)
        every { mockedMapper.apply(any()) } returns mockedBitcoinMarketPriceInformation

        // Triggering the function that we want to try
        bitcoinRepositoryImpl.fetchBitcoinMarketPriceInformation("").subscribe()

        // Assertion
        verify(exactly = 1) { mockedMemoryReactiveStore.replace(mockedBitcoinMarketPriceInformation) }
    }
}

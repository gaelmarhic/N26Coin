package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import com.gaelmarhic.presentation.features.bitcoin.entities.BitcoinMarketPriceInformationChartViewEntity
import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper
import com.gaelmarhic.presentation.testutils.RxSchedulerOverrideRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.subjects.BehaviorSubject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import polanski.option.Option.none

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class BitcoinMarketPriceChartViewModelTest {

    /**
     * Rule that swaps the background executor used by the Architecture Components with a different
     * one which executes each task synchronously.
     */
    @get:Rule
    val architectureComponentsTestRule = InstantTaskExecutorRule()

    /**
     * Rule that swaps the background executors used by RxJava2 with a different one which executes
     * each task synchronously.
     */
    @get:Rule
    val rxJavaTestRule = RxSchedulerOverrideRule()

    /**
     * Mocked [RetrieveBitcoinMarketPriceInformationUseCase] that we will use to test
     * [BitcoinMarketPriceChartViewModel].
     */
    @MockK
    private lateinit var mockedRetrieveUseCase: RetrieveBitcoinMarketPriceInformationUseCase

    /**
     * Mocked [BitcoinMarketPriceInformationChartViewEntityMapper] that we will use to test
     * [BitcoinMarketPriceChartViewModel].
     */
    @MockK
    private lateinit var mockedMapper: BitcoinMarketPriceInformationChartViewEntityMapper

    /**
     * Mocked [BitcoinMarketPriceInformation] used to be passed to the onNext function of
     * [useCaseSubject].
     */
    @MockK
    private lateinit var mockedBitcoinMarketPriceInformation: BitcoinMarketPriceInformation

    /**
     * Mocked [BitcoinMarketPriceInformationChartViewEntity] used to be returned by the apply
     * function of [mockedMapper].
     */
    @MockK
    private lateinit var mockedViewEntity: BitcoinMarketPriceInformationChartViewEntity

    /**
     * Slot used to capture the [BitcoinMarketPriceInformation] passed to the apply function.
     */
    private val mockedBitcoinMarketPriceInformationSlot = slot<BitcoinMarketPriceInformation>()

    /**
     * [BehaviorSubject] used to mock the use case's subject.
     */
    private val useCaseSubject = BehaviorSubject.create<BitcoinMarketPriceInformation>()

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var viewModel: BitcoinMarketPriceChartViewModel

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the @MockK annotated variables.
        MockKAnnotations.init(this, relaxUnitFun = true)

        // Mocking what needs to be mocked.
        every { mockedRetrieveUseCase.getBehaviorStream(none()) } returns useCaseSubject
        every { mockedMapper.apply(capture(mockedBitcoinMarketPriceInformationSlot)) } returns mockedViewEntity

        // Instantiating the class that we want to test.
        viewModel = BitcoinMarketPriceChartViewModel(mockedRetrieveUseCase, mockedMapper)
    }

    @Test
    fun `Test that the Bitcoin market price information has been mapped`() {

        // Triggering the chain.
        useCaseSubject.onNext(mockedBitcoinMarketPriceInformation)

        // Assertions
        verify(exactly = 1) { mockedMapper.apply(any()) }
        assertEquals(mockedBitcoinMarketPriceInformationSlot.captured, mockedBitcoinMarketPriceInformation)
    }

    @Test
    fun `Check that the LiveData is correctly updated when the use case emits data`() {

        // Triggering the chain.
        useCaseSubject.onNext(mockedBitcoinMarketPriceInformation)

        // Assertions
        assertEquals(viewModel.bitcoinMarketPriceInformationLiveData.value, mockedViewEntity)
    }
}

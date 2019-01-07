package com.gaelmarhic.presentation.features.bitcoin.viewmodels

import com.gaelmarhic.domain.features.bitcoin.usecases.RetrieveBitcoinMarketPriceInformationUseCase
import com.gaelmarhic.presentation.features.bitcoin.mappers.BitcoinMarketPriceInformationChartViewEntityMapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by Gaël Marhic on 07/01/2019.
 */
class BitcoinMarketPriceChartViewModelFactoryTest {

    /**
     * Mocked [RetrieveBitcoinMarketPriceInformationUseCase] that we will use to test [viewModelFactory].
     */
    @MockK
    private lateinit var mockedRetrieveUseCase: RetrieveBitcoinMarketPriceInformationUseCase

    /**
     * Mocked [BitcoinMarketPriceInformationChartViewEntityMapper] that we will use to test [viewModelFactory].
     */
    @MockK
    private lateinit var mockedMapper: BitcoinMarketPriceInformationChartViewEntityMapper

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var viewModelFactory: BitcoinMarketPriceChartViewModelFactory

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the @MockK annotated variables.
        MockKAnnotations.init(this, relaxUnitFun = true)

        // Mocking what needs to be mocked.
        every { mockedRetrieveUseCase.getBehaviorStream(any()) } returns Observable.create {}

        // Instantiating the class that we want to test.
        viewModelFactory = BitcoinMarketPriceChartViewModelFactory(mockedRetrieveUseCase, mockedMapper)
    }

    @Test
    fun `Check that the BitcoinMarketPriceChartViewModelFactory has been instantiated correctly`() {

        // Triggering the function that we want to test.
        val viewModel = viewModelFactory.create(BitcoinMarketPriceChartViewModel::class.java)

        // Assertions
        assertNotNull(viewModel)
        assertNotNull(viewModel.bitcoinMarketPriceInformationLiveData)
    }
}

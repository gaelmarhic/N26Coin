package com.gaelmarhic.domain.features.bitcoin.usecases

import com.gaelmarhic.domain.features.bitcoin.entities.BitcoinMarketPriceInformation
import com.gaelmarhic.domain.features.bitcoin.repositories.BitcoinRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.BehaviorSubject
import org.junit.Before
import org.junit.Test
import polanski.option.Option
import polanski.option.Option.none
import polanski.option.Option.ofObj

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class RetrieveBitcoinMarketPriceInformationUseCaseTest {

    /**
     * Mocked [BitcoinRepository] that we will use to test [RetrieveBitcoinMarketPriceInformationUseCase].
     */
    @MockK
    private lateinit var bitcoinRepository: BitcoinRepository

    /**
     * [BehaviorSubject] that will mock the subject used in [BitcoinRepository].
     */
    private val bitcoinRepositorySubject = BehaviorSubject.create<Option<BitcoinMarketPriceInformation>>()

    /**
     * [TestObserver] that we will use in this series of tests.
     */
    private val testObserver = TestObserver<BitcoinMarketPriceInformation>()

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var useCase: RetrieveBitcoinMarketPriceInformationUseCase

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the @MockK annotated variables.
        MockKAnnotations.init(this, relaxUnitFun = true)

        // Mocking what needs to be mocked.
        every { bitcoinRepository.getBitcoinMarketPriceInformation() } returns bitcoinRepositorySubject

        // Instantiating the class that we want to test.
        useCase = RetrieveBitcoinMarketPriceInformationUseCase(bitcoinRepository)
    }

    @Test
    fun `Check that the bitcoin market price information is unwrapped and passed on`() {

        // Mocking what needs to be mocked
        val bitcoinMarketPriceInformation = BitcoinMarketPriceInformation(
                status = "ok",
                name = "name",
                unit = "unit",
                period = "period",
                description = "description",
                values = emptyList())

        // Triggering the function that we want to test.
        useCase.getBehaviorStream(none()).subscribe(testObserver)
        bitcoinRepositorySubject.onNext(ofObj(bitcoinMarketPriceInformation))

        // Assertions
        testObserver.assertNotTerminated()
        testObserver.assertValue(bitcoinMarketPriceInformation)
    }

    @Test
    fun `Check that if the repository is empty then the data is fetched and there is no emission`() {

        // Mocking what needs to be mocked.
        every { bitcoinRepository.fetchBitcoinMarketPriceInformation(any()) } returns Completable.complete()

        // Triggering the function that we want to test.
        bitcoinRepositorySubject.onNext(none())
        useCase.getBehaviorStream(none()).subscribe(testObserver)

        // Assertions
        verify(exactly = 1) { bitcoinRepository.fetchBitcoinMarketPriceInformation(any()) }
        testObserver.assertNoValues()
        testObserver.assertNotTerminated()
    }

    @Test
    fun `Check that if there is an error, it is propagated`() {

        // Mocking what needs to be mocked.
        val throwable = mockk<Throwable>()
        every { bitcoinRepository.fetchBitcoinMarketPriceInformation(any()) } returns Completable.error(throwable)

        // Triggering the functions that we want to test.
        bitcoinRepositorySubject.onNext(none())
        useCase.getBehaviorStream(none()).subscribe(testObserver)

        // Assertions
        verify(exactly = 1) { bitcoinRepository.fetchBitcoinMarketPriceInformation(any()) }
        testObserver.assertNoValues()
        testObserver.assertError(throwable)
    }
}

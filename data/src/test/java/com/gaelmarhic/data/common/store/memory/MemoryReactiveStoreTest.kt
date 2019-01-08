package com.gaelmarhic.data.common.store.memory

import com.gaelmarhic.data.testutils.RxSchedulerOverrideRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import polanski.option.Option.none
import polanski.option.Option.ofObj

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class MemoryReactiveStoreTest {

    /**
     * Rule that swaps the background executors used by RxJava2 with a different one which executes
     * each task synchronously.
     */
    @get:Rule
    val rxJavaTestRule = RxSchedulerOverrideRule()

    /**
     * Mocked [Memory] that we will use to test [MemoryReactiveStore].
     */
    @MockK
    private lateinit var memory: Memory<String>

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var memoryReactiveStore: MemoryReactiveStore<String>

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the @MockK annotated variables.
        MockKAnnotations.init(this, relaxUnitFun = true)

        // Instantiating the class that we want to test.
        memoryReactiveStore = MemoryReactiveStore(memory)
    }

    @Test
    fun `Check that none is emitted when the store is empty`() {

        // Mocking what needs to be mocked.
        every { memory.get() } returns Maybe.empty()

        // Assertion
        memoryReactiveStore.get().test().assertValue(none())
    }

    @Test
    fun `Check that the last stored object is emitted after subscription`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { memory.get() } returns Maybe.just(testObject)

        // Triggering the function that we want to test.
        memoryReactiveStore.store(testObject)

        // Assertion
        memoryReactiveStore.get().test().assertValue(ofObj(testObject))
    }

    @Test
    fun `Check that the stream emits when an object is stored`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { memory.get() } returns Maybe.empty()
        val testObserver = memoryReactiveStore.get().test()

        // Triggering the function that we want to test.
        memoryReactiveStore.store(testObject)

        // Assertion
        testObserver.assertValueAt(1) { it == ofObj(testObject) }
    }

    @Test
    fun `Check that the stream emits when an object is replaced`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { memory.get() } returns Maybe.just(testObject)
        val testObserver = memoryReactiveStore.get().test()

        // Triggering the function that we want to test.
        memoryReactiveStore.replace(testObject)

        // Assertion
        testObserver.assertValueAt(1) { it == ofObj(testObject) }
    }

    @Test
    fun `Check that an object is correctly stored in the memory`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { memory.get() } returns Maybe.just(testObject)

        // Triggering the function that we want to test.
        memoryReactiveStore.store(testObject)

        // Assertion
        verify(exactly = 1) { memory.put(any()) }
    }

    @Test
    fun `Check that the memory is correctly cleared when the replace function is triggered`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"

        // Triggering the function that we want to test.
        memoryReactiveStore.replace(testObject)

        // Assertion
        verify(exactly = 1) { memory.clear() }
    }
}

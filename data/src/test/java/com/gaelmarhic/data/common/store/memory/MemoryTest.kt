package com.gaelmarhic.data.common.store.memory

import com.gaelmarhic.data.common.providers.TimestampProvider
import com.gaelmarhic.data.testutils.RxSchedulerOverrideRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class MemoryTest {

    companion object {

        const val TIME_OUT_MILLISECONDS = 1000L
    }

    /**
     * Rule that swaps the background executors used by RxJava2 with a different one which executes
     * each task synchronously.
     */
    @get:Rule
    val rxJavaTestRule = RxSchedulerOverrideRule()

    /**
     * Mocked [TimestampProvider] that we will use to test [Memory].
     */
    @MockK
    private lateinit var timestampProvider: TimestampProvider

    /**
     * Instance of the class that we want to test.
     */
    private lateinit var memory: Memory<String>

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Instantiating the @MockK annotated variables.
        MockKAnnotations.init(this, relaxUnitFun = true)

        // Instantiating the class that we want to test.
        memory = Memory(timestampProvider, TIME_OUT_MILLISECONDS)
    }

    @Test
    fun `Check that the get function completes with no emission when the memory is empty`() {

        memory.get().test().assertNoValues()
    }

    @Test
    fun `Check that the stored object is correctly emitted when it is still valid`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { timestampProvider.currentTimeMillis } returns 1

        // Storing the object in the memory.
        memory.put(testObject)

        // Assertion
        every { timestampProvider.currentTimeMillis } returns (TIME_OUT_MILLISECONDS - 500)
        memory.get().test().assertValue(testObject)
    }

    @Test
    fun `Check that the stored object is not emitted when it has expired`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { timestampProvider.currentTimeMillis } returns 1

        // Storing the object in the memory.
        memory.put(testObject)

        // Assertion
        every { timestampProvider.currentTimeMillis } returns (TIME_OUT_MILLISECONDS + 500)
        memory.get().test().awaitDone(5, SECONDS).assertNoValues()
    }

    @Test
    fun `Check that the clear function correctly empties the memory`() {

        // Mocking what needs to be mocked.
        val testObject = "I am the test string!"
        every { timestampProvider.currentTimeMillis } returns 1

        // Storing the object in the memory.
        memory.put(testObject)

        // Triggering the function that we want to test.
        memory.clear()

        // Assertions
        memory.get().test().assertNoValues()
    }
}

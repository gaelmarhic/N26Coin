package com.gaelmarhic.app.features.bitcoin.activities

import android.support.test.InstrumentationRegistry.getContext
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.gaelmarhic.app.R
import com.gaelmarhic.app.testutils.constants.Constants.Companion.MOCK_WEB_SERVER_PORT
import com.gaelmarhic.app.testutils.utilities.espresso.isToast
import com.gaelmarhic.app.testutils.utilities.loadJSONFromAsset
import com.gaelmarhic.presentation.features.bitcoin.activities.BitcoinMarketPriceActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.equalToIgnoringCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Gaël Marhic on 08/01/2019.
 */
class BitcoinMarketPriceActivityTest {

    companion object {

        const val BITCOIN_MARKET_PRICE_INFORMATION_JSON_PATH = "json/bitcoin_market_price_information_30_days.json"
    }

    /**
     * [ActivityTestRule] used in this series of tests.
     */
    @get: Rule
    val activityRule = ActivityTestRule<BitcoinMarketPriceActivity>(
            BitcoinMarketPriceActivity::class.java, false, false)

    /**
     * Instance of the Activity to be tested.
     */
    private val activity by lazy { activityRule.activity }

    /**
     * [MockWebServer] used in this series of tests.
     */
    private val mockWebServer by lazy { MockWebServer() }

    /**
     * Setting up what we need for the tests.
     */
    @Before
    fun setUp() {

        // Starting the server.
        mockWebServer.start(MOCK_WEB_SERVER_PORT)
    }

    @Test
    fun testClickOnMoneyIconAndThenShowFeatureNotAvailableToast() {

        // We launch the activity.
        activityRule.launchActivity(null)

        // Clicking on the view and checking that the toast has shown.
        clickView(R.id.bottom_navigation_bar_money_icon)
        isToastMessageDisplayed(R.string.feature_not_implemented_yet)
    }

    @Test
    fun testClickOnCardIconAndThenShowFeatureNotAvailableToast() {

        // We launch the activity.
        activityRule.launchActivity(null)

        // Clicking on the view and checking that the toast has shown.
        clickView(R.id.bottom_navigation_bar_card_icon)
        isToastMessageDisplayed(R.string.feature_not_implemented_yet)
    }

    @Test
    fun testClickOnPlusIconAndThenShowFeatureNotAvailableToast() {

        // We launch the activity.
        activityRule.launchActivity(null)

        // Clicking on the view and checking that the toast has shown.
        clickView(R.id.bottom_navigation_bar_plus_icon)
        isToastMessageDisplayed(R.string.feature_not_implemented_yet)
    }

    @Test
    fun testClickOnNotificationIconAndThenShowFeatureNotAvailableToast() {

        // We launch the activity.
        activityRule.launchActivity(null)

        // Clicking on the view and checking that the toast has shown.
        clickView(R.id.bottom_navigation_bar_notification_icon)
        isToastMessageDisplayed(R.string.feature_not_implemented_yet)
    }

    @Test
    fun testClickOnUserIconAndThenShowFeatureNotAvailableToast() {

        // We launch the activity.
        activityRule.launchActivity(null)

        // Clicking on the view and checking that the toast has shown.
        clickView(R.id.bottom_navigation_bar_user_icon)
        isToastMessageDisplayed(R.string.feature_not_implemented_yet)
    }

    @Test
    fun simulateNetworkErrorAndThenShowNetworkIssueToast() {

        // We launch the activity.
        activityRule.launchActivity(null)

        // We mock the server's response.
        mockWebServer.enqueue(generateServerResponse().setResponseCode(500))

        // Checking that the toast correctly appears.
        isToastMessageDisplayed(R.string.fetching_error_message)
    }

    /**
     * Tearing down some stuff after test completion.
     */
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    /**
     * Helper function used to generate the server's response.
     */
    private fun generateServerResponse() = MockResponse()
            .setBody(loadJSONFromAsset(getContext(), BITCOIN_MARKET_PRICE_INFORMATION_JSON_PATH))

    /**
     * Helper function used to determine whether a toast message has been shown correctly.
     *
     * @param textId The id of the text to check.
     */
    private fun isToastMessageDisplayed(textId: Int) {
        onView(withText(equalToIgnoringCase(activity.getString(textId)))).inRoot(isToast())
                .check(matches(isDisplayed()))
    }

    /**
     * Helper function used to click on a view.
     *
     * @param viewId The id of the view to be clicked.
     */
    private fun clickView(viewId: Int) {
        onView(withId(viewId)).perform(click())
    }
}

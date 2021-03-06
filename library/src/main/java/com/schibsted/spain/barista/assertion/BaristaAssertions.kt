package com.schibsted.spain.barista.assertion

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoActivityResumedException
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.view.View
import com.schibsted.spain.barista.internal.assertAnyView
import com.schibsted.spain.barista.internal.failurehandler.RethrowingFailureHandler
import com.schibsted.spain.barista.internal.matcher.BooleanMatcher
import com.schibsted.spain.barista.internal.util.resourceMatcher
import org.hamcrest.Matcher
import org.junit.Assert.fail

object BaristaAssertions {

    @JvmStatic
    fun assertThatBackButtonClosesTheApp() {
        // Will launch an Exception if it closes the app
        try {
            onView(isRoot())
                    .withFailureHandler(RethrowingFailureHandler())
                    .perform(ViewActions.pressBack())
            // One of our Activities is appearing on the screen :(
            fail("The back button didn't close the app")
        } catch (noActivityException: NoActivityResumedException) {
            //Yey!
        }
    }

    /**
     * Extension function alias for [assertAnyView]
     */
    @JvmStatic
    inline fun <reified T : View> assertAny(resId: Int, assertionErrorMessage: String? = null, noinline block: (T) -> Boolean) {
        assertAny(resId.resourceMatcher(), assertionErrorMessage, block)
    }

    /**
     * Extension function alias for [assertAnyView]
     */
    @JvmStatic
    inline fun <reified T : View> assertAny(text: String, assertionErrorMessage: String? = null, noinline block: (T) -> Boolean) {
        assertAny(ViewMatchers.withText(text), assertionErrorMessage, block)
    }

    /**
     * Extension function alias for [assertAnyView]
     */
    @JvmStatic
    inline fun <reified T : View> assertAny(matcher: Matcher<View>, assertionErrorMessage: String? = null, noinline block: (T) -> Boolean) {
        assertAnyView(viewMatcher = matcher, condition = BooleanMatcher(assertionErrorMessage, block) as Matcher<View>)
    }

}

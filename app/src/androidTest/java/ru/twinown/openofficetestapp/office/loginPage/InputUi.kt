package ru.twinown.openofficetestapp.office.loginPage

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import ru.twinown.openofficetestapp.R
import ru.twinown.openofficetestapp.TextInputLayoutErrorEnabledMatcher
import ru.twinown.openofficetestapp.TextInputLayoutHasErrorText
import ru.twinown.openofficetestapp.ToastMatcher

class InputUi(
    containerConnectionIdMatcher: Matcher<View>,
    containerConnectionClassTypeMatcher: Matcher<View>
) {

    private val inputLayoutId: Int = R.id.inputLayout

    private val layoutInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputLayout::class.java),
            withId(inputLayoutId),
            containerConnectionIdMatcher,
            containerConnectionClassTypeMatcher
        )
    )

    private val portalInputInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.portalEditText)
        )
    )
    private val emailInputInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.emailEditText)
        )
    )
    private val passwordInputInteraction: ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.passwordEditText)
        )
    )

    //private val textInputLayoutErrorEnabledMatcherFalse = TextInputLayoutErrorEnabledMatcher(false)

    fun assertInitialState() {
        layoutInteraction.check(matches(isEnabled())).check(
            matches(TextInputLayoutErrorEnabledMatcher(false))
        )
        portalInputInteraction.check(matches(isDisplayed()))
        portalInputInteraction.check(matches(withText("")))
        layoutInteraction.check(matches(isEnabled())).check(
            matches(TextInputLayoutErrorEnabledMatcher(false))
        )
        emailInputInteraction.check(matches(isDisplayed()))
        emailInputInteraction.check(matches(withText("")))
        layoutInteraction.check(matches(isEnabled())).check(
            matches(TextInputLayoutErrorEnabledMatcher(false))
        )
        passwordInputInteraction.check(matches(isDisplayed()))
        passwordInputInteraction.check(matches(withText("")))
    }

    fun addPortalInput(portal: String) {
        portalInputInteraction.perform(typeText(portal), closeSoftKeyboard())
    }

    fun assertValidPortalState() {
        layoutInteraction.check(matches(TextInputLayoutErrorEnabledMatcher(false)))
    }

    fun assertInvalidPortalState() {
        portalInputInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
            .check(matches(TextInputLayoutHasErrorText(R.string.invalid_url)))
    }

    fun addEmailInput(email: String) {
        emailInputInteraction.perform(typeText(email), closeSoftKeyboard())
    }

    fun assertValidEmailAddedState() {
       layoutInteraction.check(matches(TextInputLayoutErrorEnabledMatcher(false)))
    }

    fun assertInvalidEmailState() {
        emailInputInteraction.check(matches(isEnabled()))
            .check(matches(TextInputLayoutErrorEnabledMatcher(true)))
            .check(matches(TextInputLayoutHasErrorText(R.string.invalid_email)))
    }

    fun addPasswordText(password: String) {
        passwordInputInteraction.perform(typeText(password), closeSoftKeyboard())
    }

    fun assertCorrectPasswordState() {
        onView(withId(R.id.passwordEditText))
            .check(matches(withText("Testpass123")))
    }


    fun assertInputsInvisibleDisabled() {
        portalInputInteraction.check(matches(not(isDisplayed())))
        emailInputInteraction.check(matches(not(isDisplayed())))
        passwordInputInteraction.check(matches(not(isDisplayed())))
    }

    fun assertAllInputsVisible() {
        portalInputInteraction.check(matches(isDisplayed()))
        emailInputInteraction.check(matches(isDisplayed()))
        passwordInputInteraction.check(matches(isDisplayed()))
    }


}
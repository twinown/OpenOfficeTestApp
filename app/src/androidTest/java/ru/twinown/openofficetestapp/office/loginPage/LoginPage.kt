package ru.twinown.openofficetestapp.office.loginPage

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import ru.twinown.openofficetestapp.R
import ru.twinown.openofficetestapp.ToastMatcher

class LoginPage {

    private val containerConnectionIdMatcher: Matcher<View> =
        withParent(withId(R.id.rootConnectionLayout))

    private val containerConnectionClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val inputUi = InputUi(
        containerConnectionIdMatcher = containerConnectionIdMatcher,
        containerConnectionClassTypeMatcher = containerConnectionClassTypeMatcher
    )
    private val loginButtonUi = LoginButtonUi(
            containerConnectionIdMatcher,
        containerConnectionClassTypeMatcher
    )


    fun assertInitialState() {
        assertTitleVisible()
        inputUi.assertInitialState()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleDisabled()
    }


    fun addPortalText(portal: String) {
        inputUi.addPortalInput(portal = portal)
    }

    fun assertValidPortalAddedState() {
        assertTitleVisible()
        inputUi.assertValidPortalState()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleDisabled()
    }

    fun assertInvalidPortalState() {
        assertTitleVisible()
        inputUi.assertInvalidPortalState()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleDisabled()
    }

    fun addEmailText(email: String) {
        inputUi.addEmailInput(email = email)
    }

    fun assertValidEmailAddedState() {
        assertTitleVisible()
        inputUi.assertValidEmailAddedState()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleDisabled()
    }

    fun assertInvalidEmailAddedState() {
        assertTitleVisible()
        inputUi.assertInvalidEmailState()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleDisabled()
    }

    fun addPasswordText(password: String) {
        inputUi.addPasswordText(password = password)
    }

    fun assertCorrectPasswordState(){
        assertTitleVisible()
       inputUi.assertCorrectPasswordState()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleEnabled()
    }

    fun assertWrongPasswordOrEmailState() {
        assertTitleVisible()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleEnabled()
        onView(withText("Wrong password or email")).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    fun clickLoginAndWait() {
        loginButtonUi.click()
        assertTitleNotVisible()
        inputUi.assertInputsInvisibleDisabled()
        loginButtonUi.assertLoginButtonInvisibleDisabled()
        assertProgressBarVisible()
        Thread.sleep(2000)
        assertProgressBarNotVisible()
    }

    fun assertNoInternetState() {
        assertTitleVisible()
        inputUi.assertAllInputsVisible()
        assertProgressBarNotVisible()
        loginButtonUi.assertLoginButtonVisibleDisabled()
        onView(withText("No internet connection")).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    private fun assertTitleVisible() {
        onView(withId(R.id.titleLoginText)).check(matches(isDisplayed()))
    }

    private fun assertTitleNotVisible() {
        onView(withId(R.id.titleText)).check(matches(not(isDisplayed())))
    }

    private fun assertProgressBarVisible() {
        onView(withId(R.id.loginProgressBar)).check(matches(isDisplayed()))
    }

    private fun assertProgressBarNotVisible() {
        onView(withId(R.id.loginProgressBar)).check(matches(not(isDisplayed())))
    }


}
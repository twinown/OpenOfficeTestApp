package ru.twinown.openofficetestapp.office.loginPage

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import ru.twinown.openofficetestapp.AbstractButtonUi
import ru.twinown.openofficetestapp.ButtonColorMatcher
import ru.twinown.openofficetestapp.R

class LoginButtonUi(
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>

) : AbstractButtonUi(
    onView(
        allOf(
            withId(R.id.loginButton),
            ButtonColorMatcher(""), // TODO: put color
            withText(R.string.login),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(AppCompatButton::class.java)
        )
    )
) {
    fun assertLoginButtonVisibleDisabled() {
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
        interaction.check(matches(isNotEnabled()))
            .check(matches(isCompletelyDisplayed()))
    }

    fun assertLoginButtonVisibleEnabled() {
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
        interaction.check(matches(isEnabled()))
            .check(matches(isCompletelyDisplayed()))
    }

    fun assertLoginButtonInvisibleDisabled() {
        onView(withId(R.id.loginButton)).check(matches(not(isDisplayed())))
        interaction.check(matches(isNotEnabled()))
            .check(matches(isCompletelyDisplayed()))
    }

}

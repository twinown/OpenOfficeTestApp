package ru.twinown.openofficetestapp.office.profilePage

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.twinown.openofficetestapp.ButtonUi
import ru.twinown.openofficetestapp.R

class ProfilePage {

    private val containerConnectionIdMatcher: Matcher<View> =
        withParent(withId(R.id.profileLayout))

    private val containerConnectionClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val logoutButtonUi = ButtonUi(
        id = R.id.logoutButton,
        textResId = R.string.logout,
        colorHex = "" ,//todo
        containerConnectionIdMatcher,
        containerConnectionClassTypeMatcher
    )

    fun assertProfileState() {
        assertTitleVisible()
        assertProfileImageVisible()
        assertUserNameVisible()
        assertEmailVisible()
        logoutButtonUi.assertVisible()
    }

    fun clickLogout() {
       logoutButtonUi.click()
    }

    private fun assertTitleVisible(){
        onView(withId(R.id.titleProfileText)).check(matches(isDisplayed()))
    }
    private fun assertProfileImageVisible() {
        onView(withId(R.id.avatarImageView)).check(matches(isDisplayed()))
    }
    private fun assertUserNameVisible() {
        onView(withId(R.id.userNameTextView)).check(matches(isDisplayed()))
    }
    private fun assertEmailVisible() {
        onView(withId(R.id.emailTextView)).check(matches(isDisplayed()))
    }

}

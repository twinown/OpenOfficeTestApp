package ru.twinown.openofficetestapp.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.twinown.openofficetestapp.R

class LoginPage {

    private val containerConnectionIdMatcher : Matcher<View> = withParent(withId(R.id.rootConnectionLayout))

    private val containerConnectionClassTypeMatcher : Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val inputUi = InputUi(
        containerConnectionIdMatcher =containerConnectionIdMatcher,
        containerConnectionClassTypeMatcher = containerConnectionClassTypeMatcher
    )

    fun assertInitialState() {
        assertTitleVisible()
    }

    fun assertLoginButtonDisabled() {
        TODO("Not yet implemented")
    }

    fun addPortalText(portal: String) {

    }

    fun assertValidPortalAdded() {
        TODO("Not yet implemented")
    }

    fun addEmailText(email: String) {

    }

    fun assertValidEmailAdded() {
        TODO("Not yet implemented")
    }

    fun addCorrectPasswordText(password: String) {

    }

    fun assertLoginButtonEnabled() {
        TODO("Not yet implemented")
    }

    fun clickLoginAndWait() {
        TODO("Not yet implemented")
    }

    fun assertDocumentsState() {
        TODO("Not yet implemented")
    }

    fun clickNewFolderAndWait() {
        TODO("Not yet implemented")
    }

    fun assertDocumentNewFolderState() {
        TODO("Not yet implemented")
    }

    fun clickProfileAndWait() {
        TODO("Not yet implemented")
    }

    fun assertProfileState() {
        TODO("Not yet implemented")
    }

    fun clickLogout() {
        TODO("Not yet implemented")
    }

    fun checkLoginButtonDisabled() {
        TODO("Not yet implemented")
    }

    fun assertInvalidPortalState() {
        TODO("Not yet implemented")
    }

    fun assertInvalidEmailAdded() {
        TODO("Not yet implemented")
    }

    fun addWrongPasswordText() {
        TODO("Not yet implemented")
    }

    fun assertWrongPasswordOrEmailState() {
        TODO("Not yet implemented")
    }

    fun assertNoInternetState() {
        TODO("Not yet implemented")
    }

    private fun assertTitleVisible(){
        onView(withId(R.id.titleText)).check(matches(isDisplayed()))
    }
}
package ru.twinown.openofficetestapp.office.documentPage

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import ru.twinown.openofficetestapp.R
import ru.twinown.openofficetestapp.office.loginPage.InputUi

class DocumentPage {

    fun assertDocumentsState() {
        assertTitleVisible()
        onView(withId(R.id.documentsRecyclerView))
            .check(matches(isDisplayed()))
    }

    fun clickNewFolderAndWait() {
        onView(withText("New Folder"))
            .perform(click())
        assertTitleNotVisible()
        Thread.sleep(2000) // Ждём, пока загрузится содержимое папки
        onView(withId(R.id.documentsRecyclerView))
            .check(matches(isDisplayed()))
    }


    fun assertDocumentNewFolderState() {
        assertTitleVisible()
        onView(withId(R.id.documentsRecyclerView))
            .check(matches(isDisplayed()))
        onView(withText("New document.docx"))
            .check(matches(isDisplayed()))
    }

    private fun assertTitleVisible() {
        onView(withId(R.id.titleTextDocFragment)).check(matches(isDisplayed()))
    }

    private fun assertTitleNotVisible() {
        onView(withId(R.id.titleTextDocFragment)).check(matches(not(isDisplayed())))
    }

}

package ru.twinown.openofficetestapp.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import ru.twinown.openofficetestapp.R

class InputUi (
    containerConnectionIdMatcher : Matcher<View>,
    containerConnectionClassTypeMatcher : Matcher<View>
){

    private val inputLayoutId:Int = R.id.inputLayout

    private val layoutInteraction:ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputLayout::class.java),
            withId(inputLayoutId),
            containerConnectionIdMatcher,
            containerConnectionClassTypeMatcher
        )
    )

    private val portalInputInteraction:ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.portalEditText)
        )
    )
    private val emailInputInteraction:ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.emailEditText)
        )
    )
    private val passwordInputInteraction:ViewInteraction = onView(
        allOf(
            isAssignableFrom(TextInputEditText::class.java),
            withId(R.id.passwordEditText)
        )
    )



}
package ru.twinown.openofficetestapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId

class NavigationUi {
    fun clickProfile() {
        onView(withId(R.id.bottomNavigationView))
            .perform(NavigationViewActions.navigateTo(R.id.profileFragment))
    }
}

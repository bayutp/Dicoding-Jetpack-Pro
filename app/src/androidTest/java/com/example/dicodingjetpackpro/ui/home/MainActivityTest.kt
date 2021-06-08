package com.example.dicodingjetpackpro.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.utils.IdlingResourceUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceUtils.idlingResource)
    }

    @Test
    fun moveToOtherMenu() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_tv)).perform(click())
        onView(withId(R.id.menu_bookmark)).perform(click())
        onView(withId(R.id.menu_home)).perform(click())
    }
}
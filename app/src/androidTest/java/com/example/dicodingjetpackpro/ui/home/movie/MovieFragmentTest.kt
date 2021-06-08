package com.example.dicodingjetpackpro.ui.home.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.dicodingjetpackpro.R
import com.example.dicodingjetpackpro.utils.typeSearchViewText
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.utils.IdlingResourceUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieFragmentTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResourceUtils.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResourceUtils.idlingResource)
    }

    @Test
    fun loadDiscoverMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ns_parent_detail)).perform(swipeUp())
        onView(withId(R.id.rv_related_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
    }

    @Test
    fun searchMovies() {
        onView(withId(R.id.sv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_movie)).perform(typeSearchViewText("Spiderman"))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ns_parent_detail)).perform(swipeUp())
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
    }
}
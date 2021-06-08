package com.example.dicodingjetpackpro.ui.home.tv

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

class TvFragmentTest {

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
    fun loadDiscoverTv() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ns_parent_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.ns_parent_detail)).perform(swipeUp())
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
    }

    @Test
    fun searchTv(){
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_tv)).perform(click())
        onView(withId(R.id.sv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.sv_tv)).perform(typeSearchViewText("falcon"))
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                10
            )
        )
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.ns_parent_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.ns_parent_detail)).perform(swipeUp())
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.rv_related_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
    }
}
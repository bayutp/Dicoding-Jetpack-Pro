package com.example.dicodingjetpackpro.ui.home.bookmark

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
import com.example.dicodingjetpackpro.ui.home.MainActivity
import com.example.dicodingjetpackpro.utils.IdlingResourceUtils
import org.junit.After
import org.junit.Before
import org.junit.Test

class BookmarkContentFragmentTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResourceUtils.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(IdlingResourceUtils.idlingResource)
    }

    @Test
    fun addRemoveMovieBookmark() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_bookmark)).perform(click())
        onView(withId(R.id.vp_Bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_Bookmark)).perform(swipeLeft())
        onView(withId(R.id.vp_Bookmark)).perform(swipeRight())
        onView(withId(R.id.menu_home)).perform(click())
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
        onView(withId(R.id.iv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_bookmark)).perform(click())
        onView(withId(R.id.ns_parent_detail)).perform(swipeUp())
        onView(withId(R.id.ns_parent_detail)).perform(swipeDown())
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_bookmark)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.iv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_bookmark)).perform(click())
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
    }

    @Test
    fun addRemoveTvBookmark() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_bookmark)).perform(click())
        onView(withId(R.id.vp_Bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_Bookmark)).perform(swipeLeft())
        onView(withId(R.id.vp_Bookmark)).perform(swipeRight())
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
        onView(withId(R.id.iv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_bookmark)).perform(click())
        onView(withId(R.id.ns_parent_detail)).perform(swipeUp())
        onView(withId(R.id.ns_parent_detail)).perform(swipeDown())
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_bookmark)).perform(click())
        onView(withId(R.id.vp_Bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_Bookmark)).perform(swipeLeft())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.iv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_bookmark)).perform(click())
        onView(withId(R.id.fab_back)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_back)).perform(click())
    }

}
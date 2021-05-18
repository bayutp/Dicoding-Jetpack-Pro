package com.bayuspace.academy.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.bayuspace.academy.R
import com.bayuspace.academy.utils.DataDummy
import com.bayuspace.academy.utils.EspressoIdlingResources
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyCourse = DataDummy.generateDummyCourse()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource)
    }

    @Test
    fun loadCourses() {
        //delay()
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyCourse.size
            )
        )
    }

    @Test
    fun loadDetailCourse() {
        //delay()
        onView(withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        //delay()
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse[0].title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText("Deadline ${dummyCourse[0].deadline}")))
    }

    @Test
    fun loadModule() {
        //delay()
        onView(withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        //delay()
        onView(withId(R.id.btn_start)).perform(click())
        //delay()
        onView(withId(R.id.rv_module)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        //delay()
        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }

    @Test
    fun loadBookmark() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("Bookmark")).perform(click())
        //delay()
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    /*private fun delay() {
        try {
            Thread.sleep(SERVICE_LATENCY_IN_MILLIS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }*/

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS = 3_000L
    }
}
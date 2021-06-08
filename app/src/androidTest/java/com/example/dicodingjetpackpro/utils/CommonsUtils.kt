package com.example.dicodingjetpackpro.utils

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "Change view text"
        }

        override fun getConstraints(): Matcher<View> {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(),
                ViewMatchers.isAssignableFrom(SearchView::class.java)
            )
        }

        override fun perform(uiController: UiController?, view: View?) {
            (view as androidx.appcompat.widget.SearchView).setQuery(text, true)
        }
    }
}
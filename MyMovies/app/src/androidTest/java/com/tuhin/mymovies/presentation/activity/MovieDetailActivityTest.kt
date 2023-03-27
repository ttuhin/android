package com.tuhin.mymovies.presentation.activity

import android.Manifest
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule

import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import com.tuhin.mymovies.R
import org.hamcrest.CoreMatchers

/*
UI test for MovieDetailActivity
 */

class MovieDetailActivityTest {

    @get:Rule
    var runTimePermission: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET)

    @get:Rule
    var activityRule: ActivityTestRule<MovieListActivity> =
        ActivityTestRule(MovieListActivity::class.java)

    @Throws(InterruptedException::class)
    @Before
    fun setUp() {
        Thread.sleep(2000)
        onView(withId(R.id.movieListView))
            .inRoot(
                withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, ViewActions.click()
                )
            )
        Thread.sleep(2000)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testAllItemDisplayed() {
        onView(withId(R.id.posterImage)).check(matches(isDisplayed()))
        onView(withId(R.id.ratingText)).check(matches(isDisplayed())).check(matches(not(withText(""))))
        onView(withId(R.id.overViewText)).check(matches(isDisplayed())).check(matches(not(withText(""))))
        onView(withId(R.id.titleText)).check(matches(isDisplayed())).check(matches(not(withText(""))))
    }

    @Test
    fun testToolbarTitle() {
        onView(
            CoreMatchers.allOf(
                CoreMatchers.instanceOf(TextView::class.java),
                withParent(withId(R.id.action_bar))
            )
        )
            .check(matches(withText(R.string.detail)))
    }

}
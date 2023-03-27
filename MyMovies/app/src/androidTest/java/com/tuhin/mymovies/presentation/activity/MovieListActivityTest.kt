package com.tuhin.mymovies.presentation.activity

import android.Manifest
import android.content.Intent

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions

import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule

import org.hamcrest.Matchers
import androidx.test.rule.ActivityTestRule

import com.tuhin.mymovies.R

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

import androidx.test.espresso.matcher.ViewMatchers.withParent

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.tuhin.mymovies.util.custommatcher.SwipeRefreshLayoutMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf

/*
MovieListActivity UI test
 */
@RunWith(AndroidJUnit4::class)
class MovieListActivityTest1 {

    @Before
    fun setUp() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //activityRule.launchActivity()
    }

    @After
    fun tearDown() {
    }

    @get:Rule
    var runTimePermission: GrantPermissionRule? = GrantPermissionRule.grant(Manifest.permission.INTERNET)

    @get:Rule
    var activityRule: ActivityTestRule<MovieListActivity> =
        ActivityTestRule(MovieListActivity::class.java)

    @Test
    fun test_movie_list_displayed() {
        onView(withId(R.id.movieListView))
            .check(
                matches(
                    Matchers.allOf(isDisplayed())
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun testMovieListViewItemAction() {
        Thread.sleep(5000)
        onView(withId(R.id.movieListView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun testMovieListViewScroll() {
        Thread.sleep(5000)
        val recyclerView =
            activityRule.activity.findViewById<RecyclerView>(R.id.movieListView)
        val itemCount =
            Objects.requireNonNull(recyclerView.adapter!!).itemCount
            onView(withId(R.id.movieListView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

    @Test
    @Throws(InterruptedException::class)
    fun testPullToRefreshView() {
        Thread.sleep(1000)
        onView(withId(R.id.movieListSwipeRefresh)).perform(swipeDown()).check(matches(
            SwipeRefreshLayoutMatchers.isRefreshing()))
    }

    @Test
    fun testToolbarTitle() {
        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.action_bar))))
            .check(matches(withText(R.string.app_name)))
    }
}
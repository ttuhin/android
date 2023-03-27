package com.tuhin.myphotos.presentation.activity

import com.tuhin.myphotos.R

import android.Manifest
import androidx.recyclerview.widget.RecyclerView

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import org.junit.Assert.*

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.util.*

private const val DELAY = 5000L//In milli-seconds

@LargeTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class PhotoDetailsActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityTestRule<PhotoDetailsActivity> =
        ActivityTestRule(PhotoDetailsActivity::class.java)

    @get:Rule
    var runTimePermission: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.INTERNET)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_activity_title() {
        assertEquals(
            activityRule.activity.title,
            activityRule.activity.resources.getString(R.string.app_name)
        )
    }

    @Test
    fun test_photo_details_view_visibility() {
        onView(withId(R.id.photoDetailsListView))
            .check(
                matches(
                    Matchers.allOf(isDisplayed())
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_swipe_refresh_view() {
        onView(withId(R.id.photoDetailsListSwipeRefresh)).perform(swipeDown())
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_photo_list_displayed() {
        onView(withId(R.id.photoDetailsListView))
            .check(
                matches(
                    Matchers.allOf(isDisplayed())
                )
            )
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_recycler_view_load_item() {
        runBlocking {
            val recyclerView =
                activityRule.activity.findViewById<RecyclerView>(R.id.photoDetailsListView)
            delay(DELAY)
            val itemCount =
                Objects.requireNonNull(recyclerView.adapter!!).itemCount
            assertTrue(itemCount > 0)
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_recycler_view_scroll() {

        runBlocking {
            val recyclerView =
                activityRule.activity.findViewById<RecyclerView>(R.id.photoDetailsListView)

            delay(DELAY)

            val itemCount =
                Objects.requireNonNull(recyclerView.adapter!!).itemCount
            onView(withId(R.id.photoDetailsListView))
                .inRoot(
                    RootMatchers.withDecorView(
                        Matchers.`is`(
                            activityRule.activity.window.decorView
                        )
                    )
                )
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
        }
    }
}
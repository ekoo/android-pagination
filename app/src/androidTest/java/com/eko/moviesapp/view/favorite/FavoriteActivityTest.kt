package com.eko.moviesapp.view.favorite

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.eko.moviesapp.R
import org.junit.Rule
import org.junit.Test

class FavoriteActivityTest{



    @Rule
    @JvmField
    val activityRule = ActivityTestRule(FavoriteActivity::class.java)

    @Test
    fun mainActivityTest(){
        Espresso.onView(ViewMatchers.withId(R.id.toolbar_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.vp_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorite_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.vp_favorite)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(R.id.rv_favorite_tv_show)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
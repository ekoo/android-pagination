package com.eko.moviesapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.eko.moviesapp.utils.EspressoIdlingResource
import com.eko.moviesapp.utils.FakeDataDummy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyCourse = FakeDataDummy()

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun mainActivityTest(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.vp_main)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.movie_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_detail_toolbar)).check(matches(hasDescendant(withText(dummyCourse.movieData[0].results[0].title))))
        pressBack()
        onView(withId(R.id.vp_main)).perform(swipeLeft())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_show_detail_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_detail_toolbar)).check(matches(hasDescendant(withText(dummyCourse.tvData[0].results[0].name))))
    }

}
package com.eko.moviesapp.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)


    internal fun increment() {
        espressoTestIdlingResource.increment()
    }

    internal fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    internal fun getEspressoIdlingResource(): IdlingResource{
        return espressoTestIdlingResource
    }
}
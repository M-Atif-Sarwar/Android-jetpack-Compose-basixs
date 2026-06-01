package com.example.internet_mars_image.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.TestDispatcher
import org.junit.runner.Description
import org.junit.rules.TestWatcher

class TestDispatcherRules(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {

    override fun starting(description: Description){
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
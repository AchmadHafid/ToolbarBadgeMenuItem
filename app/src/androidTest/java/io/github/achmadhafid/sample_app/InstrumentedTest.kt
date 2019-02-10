package io.github.achmadhafid.sample_app

import android.R
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test

class InstrumentedTest {

    @Test
    fun verify_MainActivity_launched() {
        launchActivity<MainActivity>()

        Espresso.onView(ViewMatchers.withId(R.id.content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}

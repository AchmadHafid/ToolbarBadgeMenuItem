package io.github.achmadhafid.sample_app

import android.R
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UnitTest {

    @Test
    fun `verify MainActivity launched`() {
        launchActivity<MainActivity>()

        Espresso.onView(ViewMatchers.withId(R.id.content))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}

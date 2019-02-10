package io.github.achmadhafid.toolbar_badge_menu_item

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test

class InstrumentedTest {

    @Test
    fun verify_package_name_is_correct() {
        val packageName = "io.github.achmadhafid.toolbar_badge_menu_item.test"
        val app         = ApplicationProvider.getApplicationContext<Application>()

        assertEquals(packageName, app.packageName)
    }

}

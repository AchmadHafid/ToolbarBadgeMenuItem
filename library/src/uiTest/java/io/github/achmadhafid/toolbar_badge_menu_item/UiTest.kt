package io.github.achmadhafid.toolbar_badge_menu_item

import android.app.Application
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTest {

    @Test
    fun verify_package_name_is_correct() {
        val packageName = "io.github.achmadhafid.toolbar_badge_menu_item.test"
        val app         = getApplicationContext<Application>()

        assertEquals(packageName, app.packageName)
    }

}

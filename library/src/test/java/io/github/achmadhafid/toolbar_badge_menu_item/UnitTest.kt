package io.github.achmadhafid.toolbar_badge_menu_item

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UnitTest {

    @Test
    fun `verify package name is correct`() {
        val packageName = "io.github.achmadhafid.toolbar_badge_menu_item.test"
        val app         = ApplicationProvider.getApplicationContext<Application>()

        Truth.assertThat(packageName)
            .isEqualTo(app.packageName)
    }

}

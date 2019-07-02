package io.github.achmadhafid.sample_app

import android.app.Application
import io.github.achmadhafid.simplepref.extension.simplePrefNullable
import io.github.achmadhafid.toolbar_badge_menu_item.initToolbarBadgeMenuItem
import io.github.achmadhafid.zpack.ktx.applyTheme

@Suppress("unused")
class DemoApp : Application() {

    private var appTheme: Int? by simplePrefNullable()

    override fun onCreate() {
        super.onCreate()
        appTheme?.let { applyTheme(it) }

        initToolbarBadgeMenuItem(
            defaultTextColorRes       = R.attr.colorSurface,  // default value
            defaultBackgroundColorRes = R.attr.colorPrimary,  // default value
            defaultIconTintRes        = R.attr.colorOnSurface // default value
        )
    }
}

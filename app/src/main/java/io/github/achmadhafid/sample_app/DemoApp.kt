package io.github.achmadhafid.sample_app

import android.app.Application
import io.github.achmadhafid.simplepref.lifecycle.SimplePrefLifecycleOwner
import io.github.achmadhafid.simplepref.lifecycle.SimplePrefLifecycleOwnerImpl
import io.github.achmadhafid.simplepref.simplePref
import io.github.achmadhafid.toolbar_badge_menu_item.setDefaultToolbarBadgeMenuItemColor
import io.github.achmadhafid.zpack.ktx.applyTheme

@Suppress("unused")
class DemoApp : Application(), SimplePrefLifecycleOwner by SimplePrefLifecycleOwnerImpl() {

    private var appTheme: Int? by simplePref("app_theme")

    override fun onCreate() {
        super.onCreate()
        attachSimplePrefContext(this)
        appTheme?.let { applyTheme(it) }

        setDefaultToolbarBadgeMenuItemColor {
            textRes       = R.attr.colorSurface   // default value
            backgroundRes = R.attr.colorPrimary   // default value
            iconTintRes   = R.attr.colorOnSurface // default value
        }
    }
}

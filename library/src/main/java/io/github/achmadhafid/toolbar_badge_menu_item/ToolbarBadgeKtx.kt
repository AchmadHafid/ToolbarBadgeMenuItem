package io.github.achmadhafid.toolbar_badge_menu_item

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.createToolbarBadge(
    menu: Menu,
    config: ToolbarBadgeConfig.() -> Unit
) = ToolbarBadgeConfig(ToolbarBadgeColor()).apply(config).let {
    menu.createToolbarBadge(it.items, it.color, activity = this)
}

fun Fragment.createToolbarBadge(
    menu: Menu,
    config: ToolbarBadgeConfig.() -> Unit
) = ToolbarBadgeConfig(ToolbarBadgeColor()).apply(config).let {
    menu.createToolbarBadge(it.items, it.color, fragment = this)
}

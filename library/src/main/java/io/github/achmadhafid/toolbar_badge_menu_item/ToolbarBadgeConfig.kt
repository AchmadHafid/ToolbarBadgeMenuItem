package io.github.achmadhafid.toolbar_badge_menu_item

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ToolbarBadgeConfig internal constructor() {
    internal constructor(color: ToolbarBadgeColor) : this() {
        this.color = color
    }

    lateinit var toolbarMenu: Menu
    lateinit var icons: Map<Int, Int>
    lateinit var color: ToolbarBadgeColor
    lateinit var count: (Int) -> Int
}

fun ToolbarBadgeConfig.withColor(color: ToolbarBadgeColor.() -> Unit) {
    this.color = ToolbarBadgeColor().apply(color)
}

fun ToolbarBadgeConfig.withCount(count: (Int) -> Int) {
    this.count = count
}

fun AppCompatActivity.createToolbarBadge(
    config: ToolbarBadgeConfig.() -> Unit
) = with(ToolbarBadgeConfig(ToolbarBadgeColor()).apply(config)) {
    ToolbarBadgeMenuItem.createToolbarBadge(
        toolbarMenu, icons, color, count,
        activity = this@createToolbarBadge
    )
}

fun Fragment.createToolbarBadge(
    config: ToolbarBadgeConfig.() -> Unit
) = with(ToolbarBadgeConfig(ToolbarBadgeColor()).apply(config)) {
    ToolbarBadgeMenuItem.createToolbarBadge(
        toolbarMenu, icons, color, count,
        fragment = this@createToolbarBadge
    )
}

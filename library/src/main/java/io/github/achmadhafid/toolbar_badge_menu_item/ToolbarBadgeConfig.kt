package io.github.achmadhafid.toolbar_badge_menu_item

import android.view.Menu

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

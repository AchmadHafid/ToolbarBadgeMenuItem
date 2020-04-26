package io.github.achmadhafid.toolbar_badge_menu_item

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

class ToolbarBadgeConfig internal constructor() {
    internal constructor(color: ToolbarBadgeColor) : this() {
        this.color = color
    }
    internal val items = hashMapOf<Int, Pair<Int, Int>>()
    lateinit var color: ToolbarBadgeColor
}

fun ToolbarBadgeConfig.addItem(@IdRes id: Int, @DrawableRes icon: Int, count: Int) {
    items[id] = icon to count
}

fun ToolbarBadgeConfig.addItem(@IdRes id: Int, @DrawableRes icon: Int, count: () -> Int) {
    items[id] = icon to count()
}

fun ToolbarBadgeConfig.withColor(color: ToolbarBadgeColor.() -> Unit) {
    this.color = ToolbarBadgeColor().apply(color)
}

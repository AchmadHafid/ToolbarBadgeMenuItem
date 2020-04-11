package io.github.achmadhafid.toolbar_badge_menu_item

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.createToolbarBadge(
    config: ToolbarBadgeConfig.() -> Unit
) = with(ToolbarBadgeConfig(ToolbarBadgeColor()).apply(config)) {
    createToolbarBadge(
        toolbarMenu, icons, color, count,
        activity = this@createToolbarBadge
    )
}

fun Fragment.createToolbarBadge(
    config: ToolbarBadgeConfig.() -> Unit
) = with(ToolbarBadgeConfig(ToolbarBadgeColor()).apply(config)) {
    createToolbarBadge(
        toolbarMenu, icons, color, count,
        fragment = this@createToolbarBadge
    )
}

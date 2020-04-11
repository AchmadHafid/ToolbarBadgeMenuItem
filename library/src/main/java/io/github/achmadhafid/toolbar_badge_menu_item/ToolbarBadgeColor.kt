package io.github.achmadhafid.toolbar_badge_menu_item

import androidx.annotation.AttrRes
import androidx.annotation.ColorRes

data class ToolbarBadgeColor(
    @ColorRes @AttrRes var textRes: Int       = DefaultToolbarBadgeColor.textRes,
    @ColorRes @AttrRes var backgroundRes: Int = DefaultToolbarBadgeColor.backgroundRes,
    @ColorRes @AttrRes var iconTintRes: Int?  = DefaultToolbarBadgeColor.iconTintRes
)

internal object DefaultToolbarBadgeColor {
    var textRes: Int       = R.attr.colorSurface
    var backgroundRes: Int = R.attr.colorPrimary
    var iconTintRes: Int?  = R.attr.colorOnSurface
}

fun setDefaultToolbarBadgeMenuItemColor(builder: ToolbarBadgeColor.() -> Unit) {
    val color = ToolbarBadgeColor().apply(builder)
    DefaultToolbarBadgeColor.apply {
        textRes       = color.textRes
        backgroundRes = color.backgroundRes
        iconTintRes   = color.iconTintRes
    }
}

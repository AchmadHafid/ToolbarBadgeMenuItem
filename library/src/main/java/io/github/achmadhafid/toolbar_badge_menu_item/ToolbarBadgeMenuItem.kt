@file:Suppress("unused")

package io.github.achmadhafid.toolbar_badge_menu_item

import android.app.Application
import android.content.res.ColorStateList
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.nekocode.badge.BadgeDrawable
import io.github.achmadhafid.zpack.ktx.resolveColor

internal object ToolbarBadgeMenuItem {

    @ColorRes @AttrRes var defaultTextColorRes: Int = R.attr.colorSurface
    @ColorRes @AttrRes var defaultBackgroundColorRes: Int = R.attr.colorPrimary
    @ColorRes @AttrRes var defaultIconTintRes: Int? = R.attr.colorOnSurface

    fun createToolbarBadge(
        menu: Menu,
        icons: Map<Int, Int>,
        @ColorRes @AttrRes textColorRes: Int = defaultTextColorRes,
        @ColorRes @AttrRes backgroundColorRes: Int = defaultBackgroundColorRes,
        @ColorRes @AttrRes iconTintRes: Int? = defaultIconTintRes,
        count: (Int) -> Int,
        activity: AppCompatActivity? = null,
        fragment: Fragment? = null
    ): Boolean {
        val context    = activity ?: fragment?.context ?: return false
        val padding    = context.resources.getDimension(R.dimen.toolbar_badge_menu_item_padding)
        val textColor  = context.resolveColor(textColorRes)
        val badgeColor = context.resolveColor(backgroundColorRes)
        val tintColor  = iconTintRes?.let { context.resolveColor(iconTintRes) }

        for ((id, icon) in icons) {
            val menuItem     = menu.findItem(id) ?: return false
            val layout       = menuItem.actionView
            val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: return false
            val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: return false

            iconDrawable.setImageResource(icon)
            tintColor?.let {
                iconDrawable.imageTintList = ColorStateList.valueOf(tintColor)
            }

            count(id).let {
                @Suppress("MagicNumber")
                if (it > 0) {
                    badge.text = BadgeDrawable.Builder()
                        .type(BadgeDrawable.TYPE_NUMBER)
                        .number(it)
                        .textColor(textColor)
                        .badgeColor(badgeColor)
                        .padding(padding, padding, padding, padding, padding)
                        .build()
                        .toSpannable()
                }
            }

            layout?.setOnClickListener {
                activity?.onOptionsItemSelected(menuItem)
                fragment?.onOptionsItemSelected(menuItem)
            }
        }

        return true
    }
}

fun Application.initToolbarBadgeMenuItem(
    @ColorRes @AttrRes defaultTextColorRes : Int = ToolbarBadgeMenuItem.defaultTextColorRes,
    @ColorRes @AttrRes defaultBackgroundColorRes : Int = ToolbarBadgeMenuItem.defaultBackgroundColorRes,
    @ColorRes @AttrRes defaultIconTintRes : Int? = ToolbarBadgeMenuItem.defaultIconTintRes
) {
    with(ToolbarBadgeMenuItem) {
        this.defaultTextColorRes       = defaultTextColorRes
        this.defaultBackgroundColorRes = defaultBackgroundColorRes
        this.defaultIconTintRes        = defaultIconTintRes
    }
}

fun AppCompatActivity.createToolbarBadge(
    menu: Menu,
    icons: Map<Int, Int>,
    @ColorRes @AttrRes textColorRes: Int = ToolbarBadgeMenuItem.defaultTextColorRes,
    @ColorRes @AttrRes backgroundColorRes: Int = ToolbarBadgeMenuItem.defaultBackgroundColorRes,
    @ColorRes @AttrRes iconTintRes: Int? = ToolbarBadgeMenuItem.defaultIconTintRes,
    count: (Int) -> Int
) = ToolbarBadgeMenuItem.createToolbarBadge(
    menu, icons, textColorRes, backgroundColorRes, iconTintRes, count, activity = this
)

fun Fragment.createToolbarBadge(
    menu: Menu,
    icons: Map<Int, Int>,
    @ColorRes @AttrRes textColorRes: Int = ToolbarBadgeMenuItem.defaultTextColorRes,
    @ColorRes @AttrRes backgroundColorRes: Int = ToolbarBadgeMenuItem.defaultBackgroundColorRes,
    @ColorRes @AttrRes iconTintRes: Int? = ToolbarBadgeMenuItem.defaultIconTintRes,
    count: (Int) -> Int
) = ToolbarBadgeMenuItem.createToolbarBadge(
    menu, icons, textColorRes, backgroundColorRes, iconTintRes, count, fragment = this
)

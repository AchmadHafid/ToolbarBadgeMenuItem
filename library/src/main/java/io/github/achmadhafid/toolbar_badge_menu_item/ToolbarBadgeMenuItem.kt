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
    menu: Menu?,
    icons: Map<Int, Int>,
    @ColorRes @AttrRes textColorRes: Int = ToolbarBadgeMenuItem.defaultTextColorRes,
    @ColorRes @AttrRes backgroundColorRes: Int = ToolbarBadgeMenuItem.defaultBackgroundColorRes,
    @ColorRes @AttrRes iconTintRes: Int? = ToolbarBadgeMenuItem.defaultIconTintRes,
    count: (Int) -> Int
): Boolean {
    menu?.let {
        for ((id, icon) in icons) {
            val menuItem     = menu.findItem(id) ?: return false
            val layout       = menuItem.actionView
            val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: return false
            val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: return false
            val padding      = resources.getDimension(R.dimen.toolbar_badge_menu_item_padding)

            iconDrawable.setImageResource(icon)
            iconTintRes?.let {
                iconDrawable.imageTintList = ColorStateList.valueOf(resolveColor(it))
            }

            count(id).let {
                @Suppress("MagicNumber")
                if (it > 0) {
                    badge.text = BadgeDrawable.Builder()
                        .type(BadgeDrawable.TYPE_NUMBER)
                        .number(it)
                        .badgeColor(resolveColor(backgroundColorRes))
                        .textColor(resolveColor(textColorRes))
                        .padding(padding, padding, padding, padding, padding)
                        .build()
                        .toSpannable()
                }
            }

            layout?.setOnClickListener {
                onOptionsItemSelected(menuItem)
            }
        }
    }

    return true
}

fun Fragment.createToolbarBadge(
    menu: Menu?,
    icons: Map<Int, Int>,
    @ColorRes @AttrRes textColorRes: Int = ToolbarBadgeMenuItem.defaultTextColorRes,
    @ColorRes @AttrRes backgroundColorRes: Int = ToolbarBadgeMenuItem.defaultBackgroundColorRes,
    @ColorRes @AttrRes iconTintRes: Int? = ToolbarBadgeMenuItem.defaultIconTintRes,
    count: (Int) -> Int
): Boolean {
    menu?.let {
        for ((id, icon) in icons) {
            val menuItem     = menu.findItem(id) ?: return false
            val layout       = menuItem.actionView
            val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: return false
            val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: return false
            val padding      = resources.getDimension(R.dimen.toolbar_badge_menu_item_padding)

            iconDrawable.setImageResource(icon)
            context?.let {ctx ->
                iconTintRes?.let {
                    iconDrawable.imageTintList = ColorStateList.valueOf(ctx.resolveColor(it))
                }
            }

            count(id).let {
                @Suppress("MagicNumber")
                if (it > 0) {
                    val builder = BadgeDrawable.Builder()
                        .type(BadgeDrawable.TYPE_NUMBER)
                        .number(it)
                    context?.let { ctx ->
                        builder.badgeColor(ctx.resolveColor(backgroundColorRes))
                            .textColor(ctx.resolveColor(textColorRes))
                    }
                    badge.text = builder.padding(padding, padding, padding, padding, padding)
                        .build()
                        .toSpannable()
                }
            }

            layout?.setOnClickListener {
                onOptionsItemSelected(menuItem)
            }
        }
    }

    return true
}

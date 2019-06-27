package io.github.achmadhafid.toolbar_badge_menu_item

import android.content.res.ColorStateList
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import cn.nekocode.badge.BadgeDrawable
import io.github.achmadhafid.zpack.ktx.resolveColor

fun AppCompatActivity.createToolbarBadge(
    menu: Menu?,
    icons: Map<Int, Int>,
    @ColorRes @AttrRes backgroundColorRes: Int = R.attr.colorOnPrimary,
    @ColorRes @AttrRes textColorRes: Int = R.attr.colorPrimary,
    @ColorRes @AttrRes iconTintRes: Int? = null,
    count: (Int) -> Int
) {
    menu?.let {
        for ((id, icon) in icons) {
            val menuItem     = menu.findItem(id) ?: continue
            val layout       = menuItem.actionView
            val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: continue
            val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: continue
            val padding      = resources.getDimension(R.dimen.badge_menu_item_padding)

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
}

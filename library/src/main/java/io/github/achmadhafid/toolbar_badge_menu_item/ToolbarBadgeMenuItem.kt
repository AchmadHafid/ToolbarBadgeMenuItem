package io.github.achmadhafid.toolbar_badge_menu_item

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import cn.nekocode.badge.BadgeDrawable

class ToolbarBadgeMenuItem {

    companion object {

        @Suppress("ReturnCount")
        @JvmStatic
        fun build(
            context: Context,
            menu: Menu,
            itemId: Int,
            @DrawableRes icon: Int,
            badgeCount: Int
        ): Pair<MenuItem?, View?> {
            val menuItem     = menu.findItem(itemId) ?: return null to null
            val layout       = menuItem.actionView
            val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: return null to null
            val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: return null to null
            val padding      = context.resources.getDimension(R.dimen.badge_menu_item_padding)

            iconDrawable.setImageResource(icon)

            @Suppress("MagicNumber")
            if (badgeCount > 0) {
                badge.text = BadgeDrawable.Builder()
                    .type(BadgeDrawable.TYPE_NUMBER)
                    .number(badgeCount)
                    .badgeColor(ContextCompat.getColor(context, R.color.badge_menu_item_background))
                    .textColor(ContextCompat.getColor(context, R.color.badge_menu_item_text))
                    .padding(padding, padding, padding, padding, padding)
                    .build()
                    .toSpannable()
            }

            return menuItem to layout
        }

    }

}

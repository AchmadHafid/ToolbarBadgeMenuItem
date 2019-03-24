package io.github.achmadhafid.toolbar_badge_menu_item

import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cn.nekocode.badge.BadgeDrawable

fun AppCompatActivity.createToolbarBadge(
    menu: Menu?,
    icons: Map<Int, Int>,
    block: (Int) -> Int
) {
    menu?.let {
        for ((id, icon) in icons) {
            val menuItem     = menu.findItem(id) ?: continue
            val layout       = menuItem.actionView
            val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: continue
            val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: continue
            val padding      = resources.getDimension(R.dimen.badge_menu_item_padding)

            iconDrawable.setImageResource(icon)

            block(id).let {
                @Suppress("MagicNumber")
                if (it > 0) {
                    badge.text = BadgeDrawable.Builder()
                        .type(BadgeDrawable.TYPE_NUMBER)
                        .number(it)
                        .badgeColor(ContextCompat.getColor(this, R.color.badge_menu_item_background))
                        .textColor(ContextCompat.getColor(this, R.color.badge_menu_item_text))
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

package io.github.achmadhafid.toolbar_badge_menu_item

import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cn.nekocode.badge.BadgeDrawable

object ToolbarBadgeMenuItem {

    @JvmStatic
    fun build(
        activity: AppCompatActivity,
        menu: Menu?,
        resources: List<Triple<Int, Int, () -> Int>>
    ) {
        menu?.let {
            for ((id, icon, badgeCount) in resources) {
                val menuItem     = menu.findItem(id) ?: continue
                val layout       = menuItem.actionView
                val iconDrawable = layout.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: continue
                val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: continue
                val padding      = activity.resources.getDimension(R.dimen.badge_menu_item_padding)

                iconDrawable.setImageResource(icon)

                badgeCount().let {
                    @Suppress("MagicNumber")
                    if (it > 0) {
                        badge.text = BadgeDrawable.Builder()
                            .type(BadgeDrawable.TYPE_NUMBER)
                            .number(it)
                            .badgeColor(ContextCompat.getColor(activity, R.color.badge_menu_item_background))
                            .textColor(ContextCompat.getColor(activity, R.color.badge_menu_item_text))
                            .padding(padding, padding, padding, padding, padding)
                            .build()
                            .toSpannable()
                    }
                }

                layout?.setOnClickListener {
                    activity.onOptionsItemSelected(menuItem)
                }
            }
        }
    }

}

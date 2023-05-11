package io.github.achmadhafid.toolbar_badge_menu_item

import android.content.res.ColorStateList
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import cn.nekocode.badge.BadgeDrawable
import io.github.achmadhafid.zpack.extension.resolveColor

@Suppress("ReturnCount")
internal fun Menu.createToolbarBadge(
    items: Map<Int, Pair<Int, Int>>,
    color: ToolbarBadgeColor,
    activity: AppCompatActivity? = null,
    fragment: Fragment? = null
): Boolean {
    val context    = activity ?: fragment?.context ?: return false
    val padding    = context.resources.getDimension(R.dimen.toolbar_badge_menu_item_padding)
    val textColor  = context.resolveColor(color.textRes)
    val badgeColor = context.resolveColor(color.backgroundRes)
    val tintColor  = color.iconTintRes?.let { context.resolveColor(it) }

    for ((id, item) in items) {
        val menuItem     = findItem(id) ?: return false
        val layout       = menuItem.actionView
        val iconDrawable = layout?.findViewById<ImageView>(R.id.badge_menu_item_icon) ?: return false
        val badge        = layout.findViewById<TextView>(R.id.badge_menu_item_text) ?: return false

        iconDrawable.setImageResource(item.first)
        tintColor?.let {
            iconDrawable.imageTintList = ColorStateList.valueOf(it)
        }

        @Suppress("MagicNumber")
        if (item.second > 0) {
            badge.text = BadgeDrawable.Builder()
                .type(BadgeDrawable.TYPE_NUMBER)
                .number(item.second)
                .textColor(textColor)
                .badgeColor(badgeColor)
                .padding(padding, padding, padding, padding, padding)
                .build()
                .toSpannable()
        }

        layout.setOnClickListener {
            activity?.onOptionsItemSelected(menuItem)
            @Suppress("DEPRECATION")
            fragment?.onOptionsItemSelected(menuItem)
        }
    }

    return true
}

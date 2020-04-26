package io.github.achmadhafid.sample_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import io.github.achmadhafid.simplepref.SimplePref
import io.github.achmadhafid.simplepref.simplePref
import io.github.achmadhafid.toolbar_badge_menu_item.addItem
import io.github.achmadhafid.toolbar_badge_menu_item.createToolbarBadge
import io.github.achmadhafid.toolbar_badge_menu_item.withColor
import io.github.achmadhafid.zpack.ktx.bindView
import io.github.achmadhafid.zpack.ktx.setMaterialToolbar
import io.github.achmadhafid.zpack.ktx.toastShort
import io.github.achmadhafid.zpack.ktx.toggleTheme

class MainActivity : AppCompatActivity(R.layout.activity_main), SimplePref {

    //region View Binding

    private val btnIncreaseBadge: MaterialButton by bindView(R.id.materialButton_increaseBadge)
    private val btnDecreaseBadge: MaterialButton by bindView(R.id.materialButton_decreaseBadge)

    //endregion
    //region Properties

    private var appTheme: Int? by simplePref()
    private var badgeCount by simplePref { 0 }

    //endregion
    //region Lifecycle Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMaterialToolbar(R.id.toolbar)

        btnIncreaseBadge.setOnClickListener {
            badgeCount++
            invalidateOptionsMenu()
        }
        btnDecreaseBadge.setOnClickListener {
            if (badgeCount > 0) badgeCount--
            invalidateOptionsMenu()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) = createToolbarBadge(menu) {
        addItem(R.id.action_show_notification, R.drawable.ic_notifications_none_white_24dp, badgeCount)
        withColor {
            textRes = R.attr.colorSurface       // default value from material components theme attribute
                                                // can also be a plain color resource (e.g. R.color.some_color)
            backgroundRes = R.attr.colorPrimary // default value from material components theme attribute
                                                // can also be a plain color resource (e.g. R.color.some_color)
            iconTintRes = R.attr.colorOnSurface // default is null (no tint)
                                                // can also be a plain color resource (e.g. R.color.some_color)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_show_notification -> {
            toastShort("$badgeCount badge" + if (badgeCount > 1) "s" else "")
            true
        }
        R.id.action_toggle_theme -> {
            appTheme = toggleTheme()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    //endregion

}

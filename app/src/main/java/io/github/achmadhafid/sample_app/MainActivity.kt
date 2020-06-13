package io.github.achmadhafid.sample_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.github.achmadhafid.sample_app.databinding.ActivityMainBinding
import io.github.achmadhafid.simplepref.SimplePref
import io.github.achmadhafid.simplepref.simplePref
import io.github.achmadhafid.toolbar_badge_menu_item.addItem
import io.github.achmadhafid.toolbar_badge_menu_item.createToolbarBadge
import io.github.achmadhafid.toolbar_badge_menu_item.withColor
import io.github.achmadhafid.zpack.extension.toastShort
import io.github.achmadhafid.zpack.extension.toggleTheme

class MainActivity : AppCompatActivity(), SimplePref {

    //region View Binding

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //endregion
    //region Properties

    private var appTheme: Int? by simplePref()
    private var badgeCount by simplePref { 0 }

    //endregion
    //region Lifecycle Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.btnIncrement.setOnClickListener {
            badgeCount++
            invalidateOptionsMenu()
        }
        binding.btnDecrement.setOnClickListener {
            if (badgeCount > 0) badgeCount--
            invalidateOptionsMenu()
        }
    }

    @Suppress("MaxLineLength")
    override fun onPrepareOptionsMenu(menu: Menu) = createToolbarBadge(menu) {
        addItem(R.id.action_show_notification, R.drawable.ic_notifications_none_white_24dp, badgeCount)
        withColor {
            textRes = R.attr.colorSurface       // default value from material components theme attribute
                                                // can also be a plain color resource (e.g. R.color.some_color)
            backgroundRes = R.attr.colorPrimary // default value from material components theme attribute
                                                // can also be a plain color resource (e.g. R.color.some_color)
            iconTintRes = R.attr.colorOnSurface // default value from material components theme attribute
                                                // can also be a plain color resource (e.g. R.color.some_color) or null (no tint)
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

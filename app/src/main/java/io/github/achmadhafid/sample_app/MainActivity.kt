package io.github.achmadhafid.sample_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import io.github.achmadhafid.toolbar_badge_menu_item.ToolbarBadgeMenuItem

class MainActivity : AppCompatActivity() {

    //region Properties

    private var badgeCount = 0

    //endregion
    //region Lifecycle Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<MaterialButton>(R.id.materialButton_increaseBadge)
            .setOnClickListener {
                badgeCount++
                invalidateOptionsMenu()
            }
        findViewById<MaterialButton>(R.id.materialButton_decreaseBadge)
            .setOnClickListener {
                if (badgeCount > 0)
                    badgeCount--
                invalidateOptionsMenu()
            }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val items = mapOf(
            R.id.action_show_notification to R.drawable.ic_notifications_none_white_24dp
        )

        menu?.let {
            for ((id, icon) in items) {
                val (menuItem, layout) = ToolbarBadgeMenuItem.build(
                    context    = this,
                    menu       = menu,
                    itemId     = id,
                    icon       = icon,
                    badgeCount = badgeCount
                )
                layout?.setOnClickListener {
                    onOptionsItemSelected(menuItem)
                }
            }
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_show_notification -> {
            Toast.makeText(this, "Do something", Toast.LENGTH_LONG).show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    //endregion

}

package xyz.teamgravity.appshortcutdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import xyz.teamgravity.appshortcutdemo.ui.theme.AppShortcutDemoTheme

class MainActivity : ComponentActivity() {

    private companion object {
        const val KEY_SHORTCUT_ID = "MainActivity_keyShortcutId"
    }

    private val viewmodel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkShortcutId(intent)
        setContent {
            AppShortcutDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(
                            space = 16.dp,
                            alignment = Alignment.CenterVertically
                        )
                    ) {
                        Text(
                            text = stringResource(
                                id = when (viewmodel.shortcut) {
                                    Shortcut.Static -> R.string.static_shortcut_clicked
                                    Shortcut.Dynamic -> R.string.dynamic_shortcut_clicked
                                    Shortcut.Pinned -> R.string.pinned_shortcut_clicked
                                    null -> R.string.na
                                }
                            )
                        )
                        Button(
                            onClick = ::createDynamicShortcut
                        ) {
                            Text(
                                text = stringResource(id = R.string.create_dynamic_shortcut)
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        checkShortcutId(intent)
    }

    private fun checkShortcutId(intent: Intent?) {
        val shortCutId = intent?.getStringExtra(KEY_SHORTCUT_ID) ?: return
        viewmodel.onShortcutChange(Shortcut.fromId(shortCutId))
    }

    private fun createDynamicShortcut() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.action = Intent.ACTION_VIEW
        intent.putExtra(KEY_SHORTCUT_ID, Shortcut.Dynamic.id)

        val shortcut = ShortcutInfoCompat.Builder(applicationContext, Shortcut.Dynamic.id)
            .setShortLabel(getString(R.string.dynamic_short_label))
            .setLongLabel(getString(R.string.dynamic_long_label))
            .setDisabledMessage(getString(R.string.dynamic_disabled_label))
            .setIcon(IconCompat.createWithResource(applicationContext, R.drawable.ic_adb))
            .setIntent(intent)
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(applicationContext, shortcut)
    }
}
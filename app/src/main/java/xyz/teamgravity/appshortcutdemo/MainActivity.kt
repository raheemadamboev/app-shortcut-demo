package xyz.teamgravity.appshortcutdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                    }
                }
            }
        }
    }

    private fun checkShortcutId(intent: Intent?) {
        val shortCutId = intent?.getStringExtra(KEY_SHORTCUT_ID) ?: return
        viewmodel.onShortcutChange(Shortcut.fromId(shortCutId))
    }
}
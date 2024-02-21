package xyz.teamgravity.appshortcutdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var shortcut: Shortcut? by mutableStateOf(null)
        private set

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onShortcutChange(value: Shortcut) {
        shortcut = value
    }
}
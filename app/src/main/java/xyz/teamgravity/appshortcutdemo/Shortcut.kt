package xyz.teamgravity.appshortcutdemo

enum class Shortcut(
    val id: String
) {
    Static("static"),
    Dynamic("dynamic"),
    Pinned("pinned");

    companion object {
        fun fromId(id: String): Shortcut = entries.first { it.id == id }
    }
}
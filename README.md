# app-shortcut-demo

<img align="right" width="296" height="600"  src="https://github.com/raheemadamboev/app-shortcut-demo/blob/main/banner.gif" />

A simple app that demonstrates using static, dynamic, and pinned App Shortcuts.

**AppShortcut Demo**

<a href="https://github.com/raheemadamboev/app-shortcut-demo/blob/main/app-debug.apk">Download demo</a>

## Notes

1. Static shortcut creates instance of activity every time it's clicked. Multiple target activities is not created but target activity always gets recreated. `onNewIntent()` is not called when target activity is on the current stack and app is started from static action even though activity's `launchMode` is set properly. Instead use dynamic or pinned shortcut if you need `onNewIntent()` usage.
2. Static shortcuts can't be disabled or removed dynamically. The app crashes if you try to do so.
3. Pinned shortcut isn't included in shortcut list when icon is long clicked.
4. Pinned shortcuts has no limit. User can create as much as he needs.
5. Pinned shortcuts can't be removed dynamically. However, they can be disabled.
6. The maximum number of shortcuts a device supports varies. Use the `ShortcutManagerCompat.getMaxShortcutCountPerActivity(context` method to determine how many shortcuts a particular device supports. 4 in most devices. However, the method is returning incorrect value 15 and only displaying last 3 of dynamic shortcuts on Samsung Galaxy S22 Ultra (Android 15).
8. Static shortcuts have higher priority than dynamic priorities. There was 1 static shortcut and 15 dynamic shortcuts were added. In the end, 1 static shortcut and last 3 dynamic shortcuts of 15 dynamic shortcuts were displayed.
9. Although other apps can't access the metadata within your shortcuts, the launcher itself can access this data. Therefore, conceal sensitive user information in this metadata.
10. If using an `activity-alias` define the meta-data in the `activity-alias` rather than the activity it targets using the `targetActivity` attribute.
11. When possible, limit short description to 10 characters.

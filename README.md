# app-shortcut-demo

<img align="right" width="296" height="600"  src="https://github.com/raheemadamboev/app-shortcut-demo/blob/main/banner.gif" />

A simple app that demonstrates using static, dynamic, and pinned App Shortcuts.

**AppShortcut Demo**

<a href="https://github.com/raheemadamboev/app-shortcut-demo/blob/main/app-debug.apk">Download demo</a>

## Notes

1. Static shortcut creates instance of activity every time it's clicked. Multiple target activities is not created but target activity always gets recreated. `onNewIntent()` is not called when target activity is on the current stack and app is started from static action even though activity's `launchMode` is set properly. Instead use dynamic or pinned shortcut if you need `onNewIntent()` usage.

Static shortcuts can't have custom intent flags. The first intent of a static shortcut always has Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK set. This means that when your app is running, all the existing activities in the app are destroyed when a static shortcut is launched. If you don't want this behavior, you can use a trampoline activity —an invisible activity that starts another activity—in Activity.onCreate(Bundle) that calls Activity.finish() https://developer.android.com/develop/ui/views/launch/shortcuts/managing-shortcuts#start-one

2. Static shortcuts can't be disabled or removed dynamically. The app crashes if you try to do so.
3. Pinned shortcut isn't included in shortcut list when icon is long clicked.
4. Pinned shortcuts has no limit. User can create as much as he needs.
5. Pinned shortcuts can't be removed dynamically. However, they can be disabled.
6. The maximum number of shortcuts a device supports varies. Use the `ShortcutManagerCompat.getMaxShortcutCountPerActivity(context` method to determine how many shortcuts a particular device supports.
7. Most launchers display a maximum of four shortcuts. For any combination of static shortcuts and dynamic shortcuts that are defined, the launcher displays a maximum of two static shortcuts and two dynamic shortcuts. For example, if you define four static shortcuts and programmatically create three dynamic shortcuts, the launcher displays the first two static shortcuts, and the two most highly-ranked dynamic shortcuts.
8. Static shortcuts have higher priority than dynamic priorities. 1 static shortcut and 15 dynamic shortcuts were added. In the end, 1 static shortcut and last 3 dynamic shortcuts of 15 dynamic shortcuts were displayed.
9. Although other apps can't access the metadata within your shortcuts, the launcher itself can access this data. Therefore, conceal sensitive user information in this metadata.
10. If using an `activity-alias` define the meta-data in the `activity-alias` rather than the activity it targets using the `targetActivity` attribute.
11. When possible, limit short description to 10 characters.

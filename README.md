# app-shortcut-demo

<img align="right" width="296" height="600"  src="https://github.com/raheemadamboev/app-shortcut-demo/blob/main/banner.gif" />

A simple app that demonstrates using static, dynamic, and pinned App Shortcuts.

**AppShortcut Demo**

<a href="https://github.com/raheemadamboev/app-shortcut-demo/blob/main/app-debug.apk">Download demo</a>

## Notes

1. Static shortcut creates instance of activity every time it's clicked. Multiple target activities is not created but target activity always gets recreated. `onNewIntent()` is not called when target activity is on the current stack and app is started from static action even though activity's `launchMode` is set properly. Instead use dynamic or pinned shortcut if you need `onNewIntent()` usage.

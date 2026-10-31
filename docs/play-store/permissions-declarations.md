# Permissions Declarations — Google Play Console

| Permission / access | Purpose and review notes |
|---|---|
| `PACKAGE_USAGE_STATS` (Usage Access) | Reads Android usage statistics for data-usage and cost features. Explain the purpose before opening Android's Usage Access settings. |
| `QUERY_ALL_PACKAGES` | Populates the Apps screen with installed applications. This is a restricted Google Play permission. Submit the required declaration and justify why broad package visibility is essential to the app's core user-facing functionality. Approval is not guaranteed. |
| `POST_NOTIFICATIONS` | Supports optional data reminders and status notifications. |
| `SYSTEM_ALERT_WINDOW` | Optional overlay used only for the Live Cost Bubble after the user enables the feature. |
| `FOREGROUND_SERVICE` and `FOREGROUND_SERVICE_SPECIAL_USE` | Runs the user-started Live Cost Bubble session. Complete the Play Console foreground-service declaration and explain the special-use subtype. |
| `INTERNET` and `ACCESS_NETWORK_STATE` | Supports Google AdMob advertising and opening links selected by the user. |
| `RECEIVE_BOOT_COMPLETED` | Supports rescheduling relevant reminders after device restart. |

## Important policy note

`QUERY_ALL_PACKAGES` is subject to Google Play's restricted-permission policy. The app must have an eligible core use case, and the declaration must accurately explain why the installed-app list is essential. If Google Play does not approve this permission, the app may need to remove broad package visibility and use a narrower package-visibility approach. Do not submit an inaccurate declaration.

## Reviewer testing notes

- No account or sign-in is required.
- Grant Usage Access to test features that depend on Android usage statistics.
- Grant Display over other apps only when testing the optional Live Cost Bubble.
- Allow notifications if notification features are being reviewed.

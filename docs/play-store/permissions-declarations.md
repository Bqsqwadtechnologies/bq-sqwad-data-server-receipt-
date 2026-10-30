# Permission Justifications for Play Console

**PACKAGE_USAGE_STATS / Usage Access**  
Required to read Android NetworkStatsManager so the app can attribute mobile data bytes to individual apps and calculate their monetary cost. Data never leaves the device.

**SYSTEM_ALERT_WINDOW (Display over other apps)**  
Optional. Used only for the user-initiated Live Cost Bubble overlay. Clearly explained and skippable.

**FOREGROUND_SERVICE + SPECIAL_USE**  
Used solely for the Live Cost Bubble while the user has explicitly turned it on. Stops when screen is off or user stops it.

**POST_NOTIFICATIONS**  
Runway, leak, budget and live-bubble notifications. User can disable channels.

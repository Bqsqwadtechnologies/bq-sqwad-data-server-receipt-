# Permissions Declarations — Play Console

| Permission | Why |
|------------|-----|
| PACKAGE_USAGE_STATS (Usage Access) | Read Android per-app network usage statistics to calculate cost. Prominent disclosure shown before request. |
| POST_NOTIFICATIONS | Runway, spike, budget, and weekly receipt reminders. |
| SYSTEM_ALERT_WINDOW | Optional Live Cost Bubble overlay only. |
| FOREGROUND_SERVICE + SPECIAL_USE | Live Cost Bubble user-initiated session monitoring. |
| INTERNET / ACCESS_NETWORK_STATE | Ads (AdMob) and opening user-chosen links. |
| RECEIVE_BOOT_COMPLETED | Reschedule notifications after reboot. |

**Not requested:** CALL_PHONE, READ_SMS, VPN-related, health, location, contacts, storage (beyond app-specific).

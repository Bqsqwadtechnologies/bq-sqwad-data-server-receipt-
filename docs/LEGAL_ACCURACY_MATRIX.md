# Legal Accuracy Matrix

Maps key claims in the Privacy Policy and Terms to the current codebase.

| Claim | Supported by | Notes |
|-------|--------------|-------|
| No account / no sign-in | Architecture, no auth modules | True |
| Usage stats processed only on device | UsageRepository, Room, DataStore | True – no upload code |
| No VPN | Manifest, no VpnService | True |
| No traffic content inspection | No packet capture / VPN | True |
| Usage Access disclosure before system settings | UsageAccessDisclosureScreen | Present |
| Notifications permission used for alerts | Manifest + planned WorkManager | Structure present |
| Overlay only for Live Cost Bubble | LiveCostBubbleService, permission helper | True |
| Internet for ads and user-chosen links | AdMob, UriHandler | True |
| AdMob + UMP | AdManager, ConsentManager | True |
| Rewarded ads unlock features for 24h only | UnlockStore, RewardedGate | True |
| No cash / airtime / prize rewards | Code review | True |
| Clipboard only on user Paste | Paste feature (structure) | User-initiated only |
| Dialer only on user tap, no CALL_PHONE | Carrier buttons (structure) | True |
| Children under 13 not targeted | Policy statement | True |
| Delete via reset or uninstall | Settings reset path + uninstall | Reset path to be fully wired |
| Estimates can differ from carrier | CostEngine + Calibrate | True |
| No financial advice / no money movement | Feature design | True |
| Governing law Nigeria | Terms | True |
| Contact hello@bqsqwadai.com | config/company.json | True |

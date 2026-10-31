# Play Account Type Check — BQ Sqwad Data Receipt

**Goal:** Keep the app publishable under a **personal** Google Play developer account. Avoid the four triggers that require an organization account.

## 1. VPN / VpnService
- **Checked:** Source tree, AndroidManifest, merged-manifest expectations, dependencies.
- **Result:** No `VpnService`, no `BIND_VPN_SERVICE`, no packet capture, no traffic interception, no local VPN tunnel.
- **Proof:** Manifest only contains INTERNET, ACCESS_NETWORK_STATE, PACKAGE_USAGE_STATS, POST_NOTIFICATIONS, SYSTEM_ALERT_WINDOW, FOREGROUND_SERVICE, FOREGROUND_SERVICE_SPECIAL_USE, RECEIVE_BOOT_COMPLETED. No VPN-related permission or service.
- **Status:** PASS

## 2. Financial products and services
- **Checked:** Features, copy, billing flows.
- **Result:** App only estimates the cost of the user’s own mobile data from numbers the user enters. No payments, wallets, money transfer, loans, credit, insurance, investments, trading, crypto, airtime/data purchase, points that can be cashed out.
- **Naming:** Feature is called **Data Budget** (not savings account or financial plan). Hotspot Split is a calculator only.
- **Status:** PASS — declaration answer: “My app doesn’t provide any financial features.”

## 3. Health
- **Checked:** Permissions, Health Connect, medical claims.
- **Result:** No health permissions, no Health Connect, no medical or mental-health claims.
- **Status:** PASS

## 4. Government
- **Checked:** Positioning and copy.
- **Result:** Not presented as official or on behalf of any government body.
- **Status:** PASS

## Recommended Play Console answers
- **Category:** Tools
- **Financial features:** My app doesn’t provide any financial features.
- **Health:** No health features.
- **VPN:** Not a VPN app (never declare or mention VPN).
- **Ads:** Yes (AdMob).
- **Advertising ID:** Yes (via AdMob).
- **Foreground service:** Special use — Live Cost Bubble for user-initiated session monitoring of data cost.

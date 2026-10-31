# FINAL STATUS — BQ Sqwad Data Receipt

**Repository:** main branch  
**Package:** com.bqsqwad.datareceipt  
**Version:** 1.0.1 (versionCode 2)  
**minSdk 26 · targetSdk 35 · compileSdk 35**

## Done and connected on main

### Navigation & UI
- 5-tab bottom navigation
- Onboarding (Welcome / Benefits / Ready / Get started / Demo)
- Theme picker (6 themes + Follow system) with DataStore persistence
- Country & Currency picker with persistence
- Settings rows all navigate
- Company Plate (Privacy / Terms / Copyright / More from BQ Sqwad Technologies)
- About screen (package, version, links)
- More from BQ Sqwad Technologies (Recommended apps, TikTok, YouTube)
- Legal screens offline + View online

### Tools (all 8 navigate to real screens)
1. Live Cost Bubble — RewardedGate (loading / success / failure / retry) + 24h unlock
2. Cost Calculator — interactive file / video / music estimates
3. Hotspot Split — calculator only, copy share message (Play-safe)
4. Data Budget — monthly limit + 50/80/100% alerts (not financial product)
5. Calibrate — carrier balance entry
6. Leak Detector — explanation + Usage Access guidance
7. Travel Mode — toggle + second bundle/currency note
8. Best Plan Finder — structure + empty plans library warning

### Ads
- Real production unit IDs for existing banners / rewarded / interstitial
- Inline content banners (not above nav bar)
- UMP ConsentManager before MobileAds.initialize
- UnlockStore 24h
- New unit keys reserved empty (never invented)

### Play guardrails
- No VpnService / no financial products / no health / no government
- account-type-check.md PASS
- Recommended category: Tools
- Financial declaration: “My app doesn’t provide any financial features.”
- Usage Access prominent disclosure screen

### Legal
- Privacy Policy + Terms rewritten (company voice)
- privacy.html / terms.html synced
- LEGAL_ACCURACY_MATRIX.md

### Config
- config/company.json
- config/carriers.json (empty codes — owner fills)
- config/plans.json (empty — owner fills)

## Still partial / needs local Android build to finish
- Full NetworkStatsManager per-UID collection + Room daily snapshots
- Live Bubble overlay sampling + startForeground full loop
- Receipt Canvas generation + share + interstitial trigger
- Data Wrapped / Time Machine / Data Coach / Spike full logic
- verifyPlayGuard / verifyNoDeadClicks Gradle tasks
- Logo density generation (script detects missing)
- Instrumentation tests

## Owner must do
1. Upload logos to branding/ → python tools/process_logos.py
2. Fill carrier USSD codes in config/carriers.json (do not invent)
3. Add keystore secrets → manual workflow_dispatch only
4. Create any new AdMob units and paste IDs into gradle.properties
5. Test on real device (list below)

## Device test checklist (all at once)
1. Fresh install → Onboarding → Get started / Demo
2. Settings → Theme (change and restart)
3. Settings → Country & Currency
4. Home → Grant Usage Access
5. Tools → Live Bubble → Unlock ad (loading / success / fail / retry)
6. Tools → Calculator (enter values)
7. Tools → Hotspot Split (copy message)
8. Tools → Data Budget / Calibrate / Leak / Travel / Plan Finder
9. Settings → Privacy / Terms / Copyright / About / More
10. More → TikTok + YouTube buttons
11. Company Plate links
12. Banners visible inside content (not over nav)
13. All 6 themes still readable
14. Offline open of legal screens

## Known limitations
- No full NetworkStats engine yet (requires device testing)
- Receipt sharing not fully implemented
- Some advanced Part 2 features (Wrapped, Coach, Spike) are structured but not fully algorithmically complete
- Build verification requires local Android SDK / CI with secrets

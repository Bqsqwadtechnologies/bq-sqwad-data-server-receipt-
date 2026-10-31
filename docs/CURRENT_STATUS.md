# Current Status — BQ Sqwad Data Receipt (main)

**Version:** 1.0.1 (versionCode 2)  
**Latest focus:** Phase 12

## Fully connected ✅
- Bottom navigation (5 tabs)
- Onboarding (Welcome → Benefits → Ready → Get started / Try demo)
- Settings → Theme picker (persists)
- Settings → Country & Currency picker (persists to DataStore)
- Settings → Privacy / Terms / Copyright / About / More from BQ Sqwad Technologies / Help
- Company Plate links everywhere
- Tools → Live Cost Bubble (with full RewardedGate: loading / success / failure / retry)
- Tools → Cost Calculator (real estimates for file / video / music)
- Tools → remaining 6 cards navigate to dedicated screens
- Grant Usage Access on Home opens system settings
- AdMob banners on Home / Apps / Tools / Receipts
- UMP ConsentManager before ads initialize
- UnlockStore (24-hour rewards)
- CostEngine (BigDecimal)
- TikTok & YouTube open from More screen

## Partial 🟡
- Usage engine (NetworkStats structure present, full per-UID collection still needed)
- Live Cost Bubble start service (unlock works, overlay sampling next)
- Receipt Preview / share / Canvas
- Bundle editor
- Widget & Quick Settings tile (stubs)
- Logo pipeline (detects missing files)

## Not yet full ❌
- Real daily Room snapshots from NetworkStatsManager
- Full runway / leak algorithms
- Native ads
- Dead-tap Gradle verification task
- Complete multi-language files
- Adaptive icon generation from uploaded logo

## Owner next steps
1. Upload real logos into `branding/` then run `python tools/process_logos.py`
2. Add keystore secrets → run manual workflow
3. Test on device: Onboarding, Theme, Country, Live Bubble unlock ad, Calculator, Grant Access, Company Plate, More screen
4. Create any future AdMob units and paste into `gradle.properties`

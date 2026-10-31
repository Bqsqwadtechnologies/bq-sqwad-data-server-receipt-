# Current Status — BQ Sqwad Data Receipt (main)

**Version:** 1.0.1 (versionCode 2)  
**Last major push:** Phase 9–10

## Fully connected ✅
- Bottom navigation (5 tabs)
- Settings → every major row navigates
- Company Plate → Privacy, Terms, Copyright, More from BQ Sqwad Technologies
- Tools → all 8 cards navigate
- Theme picker + DataStore persistence
- Legal screens (offline + View online)
- More from BQ Sqwad Technologies (Recommended cards, TikTok, YouTube)
- Onboarding flow (Welcome → Benefits → Ready → Get started / Try demo)
- Grant Usage Access deep-link on Home
- AdMob banners on Home / Apps / Tools / Receipts
- RewardedGate (loading / success / failure states)
- UnlockStore (24 h)
- ConsentManager (UMP before ads)
- CostEngine (BigDecimal) + unit tests skeleton

## Partial / scaffold 🟡
- Usage engine (NetworkStatsManager) – structure present, full bucket query still needed
- Live Cost Bubble service – foreground service stub + notification exists
- Receipt Preview / share / Canvas generation
- Full onboarding country/currency/bundle steps
- Widget & Quick Settings tile (stubs present)
- Logo pipeline script (detects missing, full resize needs Pillow)

## Not yet implemented ❌
- Real per-app NetworkStats collection and Room snapshots
- Full runway / leak detector algorithms
- Native ads in lists
- Dead-tap Gradle verification task
- Complete localization files beyond English
- Full adaptive icon density generation from uploaded logo

## Owner next steps
1. Upload real logos into branding/ folders
2. Run `python tools/process_logos.py`
3. Add keystore secrets and run the manual build workflow
4. Test on a real device (especially Usage Access + ads)
5. Create any future AdMob units and paste IDs into gradle.properties

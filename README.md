# BQ Sqwad Data Receipt

**See what your data really costs.**

Native Android app by **BQ Sqwad Technologies** that turns mobile data usage into clear money receipts.

- Package: `com.bqsqwad.datareceipt`
- Version: 1.0.1 (versionCode 2)
- No account, no server, fully on-device
- Sky blue & white design system with 6 themes

## Current status (main branch)

- Navigation fully connected (Settings, Tools, Company Plate, More from BQ Sqwad Technologies, Theme picker, Legal screens)
- Onboarding flow present
- AdMob integrated (banners + rewarded + interstitial) with real unit IDs
- UMP consent before ads
- RewardedGate with loading / success / failure states
- Theme persistence via DataStore
- Grant Usage Access deep-link working

## Build

```bash
# Generate wrapper if missing
gradle wrapper --gradle-version 8.11.1

./gradlew assembleDebug
./gradlew assembleRelease bundleRelease
```

## Signing

Use the keystore and secrets previously provided. Workflow is manual (`workflow_dispatch` only).

## Logos

Upload logos under `branding/` then run:

```bash
python tools/process_logos.py
```

## Company config

Edit `config/company.json` — single source of truth for legal links, more apps, and social.

## Website

Static site in `docs/`. Enable GitHub Pages (main /docs) or host elsewhere.

## License

Proprietary — BQ Sqwad Technologies. All rights reserved.

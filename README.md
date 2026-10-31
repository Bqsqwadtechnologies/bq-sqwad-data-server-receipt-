# BQ Sqwad Data Receipt

**See what your data really costs.**

Native Android app by **BQ Sqwad Technologies**.

| | |
|---|---|
| Package | `com.bqsqwad.datareceipt` |
| Version | 1.0.1 (versionCode 2) |
| minSdk / targetSdk | 26 / 35 |

## Highlights on main
- Onboarding, 6 themes, country/currency
- All 8 Tools screens connected (Live Bubble + rewarded ad, Calculator, Hotspot Split, Data Budget, Calibrate, Leak Detector, Travel Mode, Plan Finder)
- Settings, Company Plate, More from BQ Sqwad Technologies, Legal screens
- Inline content banners + UMP consent + real AdMob units
- Play-safe: no VPN, no financial products, Tools category
- CostEngine (BigDecimal) + unit tests

## Build
```bash
gradle wrapper --gradle-version 8.11.1   # if wrapper missing
./gradlew assembleDebug
./gradlew assembleRelease bundleRelease
```

## Logos
Upload into `branding/` then:
```bash
python tools/process_logos.py
```

## Config
- `config/company.json` — company, social, more apps
- `config/carriers.json` — USSD codes (owner fills, do not invent)
- `config/plans.json` — optional plan library

## Docs
- `docs/FINAL_STATUS.md` — full status + device test checklist
- `docs/play-store/account-type-check.md` — personal account safe
- `docs/LEGAL_ACCURACY_MATRIX.md`

## License
Proprietary — BQ Sqwad Technologies. All rights reserved.

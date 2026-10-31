# AdMob units — create these in AdMob

**App ID (already exists):**  
`ca-app-pub-2748789567113929~6461314526`  
Package: `com.bqsqwad.datareceipt`

---

## Already created (do not recreate)

| # | AdMob name to use | Type | Unit ID |
|---|-------------------|------|--------|
| 1 | data_receipt_banner_home | **Banner** | `ca-app-pub-2748789567113929/4389684958` |
| 2 | data_receipt_banner_apps | **Banner** | `ca-app-pub-2748789567113929/3643579493` |
| 3 | data_receipt_banner_tools | **Banner** | `ca-app-pub-2748789567113929/9450439949` |
| 4 | data_receipt_banner_receipt | **Banner** | `ca-app-pub-2748789567113929/2689590471` |
| 5 | data_receipt_rewarded_live_bubble | **Rewarded** | `ca-app-pub-2748789567113929/1707681299` |
| 6 | data_receipt_rewarded_premium_receipt | **Rewarded** | `ca-app-pub-2748789567113929/1184937114` |
| 7 | data_receipt_rewarded_extended_history | **Rewarded** | `ca-app-pub-2748789567113929/6248515354` |
| 8 | data_receipt_interstitial_share | **Interstitial** | `ca-app-pub-2748789567113929/2309270343` |

---

## NEW units you must create (7 more)

Create each in AdMob → your app → **Add ad unit**. Use the **exact Ad type** below.

| # | Suggested AdMob name | Ad type to select in AdMob | Where it shows in the app | gradle.properties key |
|---|----------------------|----------------------------|---------------------------|------------------------|
| 9 | `data_receipt_banner_settings` | **Banner** | Settings screen, between section cards | `ADMOB_BANNER_SETTINGS` |
| 10 | `data_receipt_banner_detail` | **Banner** | App Detail / Runway / tool result screens | `ADMOB_BANNER_DETAIL` |
| 11 | `data_receipt_native_apps` | **Native** | Apps list every 8th row | `ADMOB_NATIVE_APPS` |
| 12 | `data_receipt_native_receipts` | **Native** | Receipts history every 5th row | `ADMOB_NATIVE_RECEIPTS` |
| 13 | `data_receipt_rewarded_insights` | **Rewarded** | Data Coach full plan + Weekly Insights (24h) | `ADMOB_REWARDED_INSIGHTS` |
| 14 | `data_receipt_rewarded_pass` | **Rewarded** | All-Access Pass (all premium 24h) | `ADMOB_REWARDED_PASS` |
| 15 | `data_receipt_rewarded_wrapped` | **Rewarded** | Data Wrapped premium styles (24h) | `ADMOB_REWARDED_WRAPPED` |

**Total planned inventory: 15 units** (8 done + 7 new).

### How to create (AdMob)
1. AdMob → Apps → **BQ Sqwad Data Receipt** (or create app with package `com.bqsqwad.datareceipt`)
2. **Add ad unit**
3. Pick type: Banner / Native / Rewarded (as in the table)
4. Name it exactly as in the “Suggested AdMob name” column
5. Copy the unit ID (`ca-app-pub-…/……`)
6. Paste into repo `gradle.properties` next to the matching key

### After you create them
Reply with the 7 new unit IDs (or paste the AdMob confirmation text). They will be wired into debug + release in one go.

### Rules already in the app
- Debug builds always use Google **test** units
- Empty production ID = that slot stays **off** (never invents IDs)
- Banners are **inline in content**, not above the bottom nav
- Rewarded only unlocks app features for 24 hours (no cash/airtime)

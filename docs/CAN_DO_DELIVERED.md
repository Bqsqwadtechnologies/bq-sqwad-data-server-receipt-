# Extra delivery — everything that could be done without a device

## Added in this pass
1. **ReceiptPreviewScreen** — paper-style receipt, Classic/Sky/Night/Minimal chips, premium lock via RewardedGate, Share intent + interstitial after share
2. **DataWrappedScreen** — 8 swipeable story slides (free style)
3. **DataCoachScreen** — rule-based tips with deep-links to system settings
4. **verify_no_dead_clicks.py** — source scan for empty click handlers
5. **verify_play_guard.py** — fail on VPN/health tokens; warn on sensitive words
6. **process_logos.py v2** — format detection, UPLOAD_LOGO_HERE.md, LOGO_STATUS.md
7. **HotspotSplitTest** + existing CostEngine tests
8. Routes + NavHost wired for Receipt Preview, Wrapped, Coach

## Still needs device / local SDK
- Full NetworkStats collection
- Live Bubble overlay sampling
- Real PNG receipt bitmap export
- Production ad fill confirmation
- assembleRelease verification

## How to run new checks locally
```bash
python tools/verify_no_dead_clicks.py
python tools/verify_play_guard.py
python tools/process_logos.py
./gradlew testDebugUnitTest
```

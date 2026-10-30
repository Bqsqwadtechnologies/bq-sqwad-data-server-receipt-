# Release Checklist

1. Upload real logos under branding/ and rebuild icons.
2. Replace AdMob test IDs in gradle.properties with production IDs.
3. Fill config/company.json (email, address, Play links).
4. Create keystore secrets in GitHub (KEYSTORE_BASE64, etc.) if using the workflow.
5. Build signed AAB: `./gradlew bundleRelease`
6. Upload AAB to Play Console internal/closed testing.
7. Complete Data safety form, permissions declarations, content rating.
8. Enable GitHub Pages (main /docs) or host docs/ elsewhere and paste Privacy URL.
9. Have a lawyer review Privacy & Terms.
10. Submit for review.

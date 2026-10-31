# Google Play requirements (as of 31 Oct 2026)

## Target API
| Rule | Level |
|------|--------|
| New apps & updates (phones/tablets) | **API 36 (Android 16)** |
| This project | `compileSdk = 36` · `targetSdk = 36` · `minSdk = 26` |

## Toolchain on main
| Tool | Version |
|------|--------|
| AGP | 8.9.1 (supports API 36) |
| Gradle | 8.11.1 |
| JDK | 17 |
| Build-Tools | 36.0.0 |
| Kotlin | 2.0.21 |

## Also configured
- Predictive back: `android:enableOnBackInvokedCallback="true"`
- 16 KB page-size path: `jniLibs.useLegacyPackaging = false`
- AAB + APK both produced by the manual workflow
- Debug and Release both use the same SDK levels
- No VPN / financial / health triggers

## Version
- versionName **1.0.2** · versionCode **3**

## Your next step
Run the GitHub Actions workflow once. Download **debug-apk** first, then **release-aab** for Play Console after secrets are set.

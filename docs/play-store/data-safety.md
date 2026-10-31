# Data Safety — Play Console Working Notes

**Application:** BQ Sqwad Data Receipt  
**Operator:** BQ Sqwad Technologies

This document is a preparation guide, not a substitute for checking the exact behaviour of the release build and every SDK. Google Play requires the developer to declare collection and sharing by the app and its third-party SDKs.

## Information processed only on the device

The current app code processes the following locally for app functionality:

- Android network-usage statistics used by data-usage and cost features.
- Installed-app names and package details used to populate the Apps screen.
- Data-bundle entries, preferences and local app settings.
- Session network counters displayed by the Live Cost Bubble.

These locally processed items are not sent to BQ Sqwad Technologies' own server in the current app design. Local-only processing is generally not reported as data collected in the Play Data safety form, but verify the final build and all included SDKs before submitting.

## Third-party advertising

Google AdMob is included in the app. Advertising SDKs may collect or share information such as:

- Device or other IDs, including the advertising ID.
- Device and advertising interaction information.
- Diagnostics or fraud-prevention information, depending on the SDK version and configuration.

Review the exact Google Mobile Ads SDK disclosures and the Google Play SDK Index for the version included in the release. Declare every applicable data type, purpose, collection and sharing status in the Play Console form. Do not mark all data types as “not collected” while an included SDK collects or shares them.

Google's User Messaging Platform is also included for consent management. Reflect its applicable processing in the review of third-party SDK practices.

## Security and deletion

- Advertising requests use network connections protected by the relevant Google SDK transport security.
- Locally stored app information can be cleared through the in-app reset function or by uninstalling the app.
- Advertising-provider data is handled under the provider's policies and retention controls; local reset does not delete data held by an advertising provider.

## Before submitting

1. Review the final merged manifest and all SDKs in the release AAB.
2. Check the current AdMob and User Messaging Platform data disclosures.
3. Complete the Play Console Data safety form to match actual SDK behaviour.
4. Ensure the Privacy Policy and Data safety answers are consistent.
5. Revisit the form whenever app permissions, SDK versions or data practices change.

Official guidance: https://support.google.com/googleplay/android-developer/answer/10787469

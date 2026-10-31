package com.bqsqwad.datareceipt.ui.screens.legal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.components.CompanyPlate

enum class LegalType { PRIVACY, TERMS, COPYRIGHT }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LegalScreen(type: LegalType, navController: NavController) {
    val title = when (type) {
        LegalType.PRIVACY -> stringResource(R.string.privacy_policy)
        LegalType.TERMS -> stringResource(R.string.terms_conditions)
        LegalType.COPYRIGHT -> stringResource(R.string.copyright_notices)
    }
    val uriHandler = LocalUriHandler.current
    val onlineUrl = when (type) {
        LegalType.PRIVACY -> "https://bqsqwadtechnologies.github.io/bq-sqwad-data-server-receipt-/privacy.html"
        LegalType.TERMS -> "https://bqsqwadtechnologies.github.io/bq-sqwad-data-server-receipt-/terms.html"
        LegalType.COPYRIGHT -> "https://bqsqwadtechnologies.github.io/bq-sqwad-data-server-receipt-/copyright.html"
    }
    val policyText = when (type) {
        LegalType.PRIVACY -> """
            BQ SQWAD TECHNOLOGIES

            PRIVACY POLICY
            Application: BQ Sqwad Data Receipt
            Last updated: 31 October 2026

            1. APPLICATION AND OPERATOR
            BQ Sqwad Data Receipt is provided by BQ Sqwad Technologies. The Application helps users review Android network-usage information, enter data-bundle details, estimate data costs and prepare shareable data receipts.

            2. INFORMATION PROCESSED ON THE DEVICE
            The Application processes Android network-usage statistics, installed-application names and package details, data-bundle information entered by the user, application preferences and session network counters used by the optional Live Cost Bubble.

            This information is processed locally and stored in the Application's private storage. The Application does not send locally processed usage information or installed-app lists to BQ Sqwad Technologies' servers. No Application account is required.

            3. PERMISSIONS
            Usage Access supports Android usage-statistics features. Installed-app visibility supports the installed-app list. Notifications support reminders. Display over other apps is optional and is used for the Live Cost Bubble. Foreground service access keeps a user-started bubble session active. Internet and network-state access support advertising and user-selected links.

            The Application does not use a VPN, intercept internet traffic or inspect the contents of websites, messages or other application traffic.

            4. ADVERTISING AND THIRD-PARTY SERVICES
            Advertisements may be provided by Google AdMob. Google and its advertising technology providers may process the advertising identifier, device information, diagnostics and ad-interaction information for advertising, measurement and fraud prevention, subject to applicable policies and consent choices.

            Where required, Google's User Messaging Platform presents an advertising consent form. Privacy and advertising choices are available in the Application's privacy settings. Rewarded advertisements may unlock selected features for a stated period and do not provide cash, airtime, mobile data or prizes.

            5. INFORMATION SHARING
            BQ Sqwad Technologies does not sell locally stored network-usage statistics, installed-app lists, bundle entries or Application preferences. Google AdMob and related Google services may process information needed to provide advertising and consent management. Third-party links are governed by the relevant provider's privacy policy.

            6. RETENTION AND DELETION
            Locally processed information remains in the Application's private storage until cleared through an available reset function or the Application is uninstalled. Advertising-related information is handled under the relevant provider's retention practices.

            7. CHILDREN'S PRIVACY
            The Application is not designed or directed to children under 13. BQ Sqwad Technologies does not knowingly request personal information from children under 13.

            8. PRIVACY RIGHTS AND CHOICES
            Privacy rights depend on applicable law and may include access, correction, objection or deletion. Device-stored information can be cleared through the Application's reset option or by uninstalling the Application. Advertising consent choices can be reviewed through the available privacy settings.

            9. POLICY UPDATES
            This policy may be updated when Application features, legal requirements or data practices change. The revised date will appear on the published policy page.

            10. CONTACT
            BQ Sqwad Technologies
            Privacy enquiries: hello@bqsqwadai.com
        """.trimIndent()
        LegalType.TERMS -> """
            BQ SQWAD TECHNOLOGIES

            TERMS AND CONDITIONS
            Application: BQ Sqwad Data Receipt
            Last updated: 31 October 2026

            1. ACCEPTANCE
            Downloading, installing or using BQ Sqwad Data Receipt indicates acceptance of these Terms and Conditions. Anyone who does not accept the terms should discontinue use and uninstall the Application.

            2. APPLICATION FEATURES
            The Application provides tools for reviewing Android network-usage statistics, entering data-bundle information, estimating data costs, viewing installed applications and creating data receipts. Optional features may include notifications and the Live Cost Bubble.

            3. ESTIMATES AND CARRIER INFORMATION
            Usage, cost, balance and expiry figures are estimates based on information made available by Android and details entered by the user. Figures may differ from a mobile network operator's records because of rounding, delayed reporting, zero-rating, background activity, network adjustments or carrier-specific factors.

            The Application is not a carrier billing system and does not replace official account statements, airtime balances or data-bundle records. Exact agreement with carrier charges or balances is not guaranteed.

            4. USER RESPONSIBILITIES
            Users are responsible for providing accurate bundle information and reviewing device permissions. The Application must not be used for unlawful activity, interference with another person's device or violation of third-party rights.

            5. PERMISSIONS
            Some features require Usage Access, installed-application visibility, notifications or optional display-over-other-apps access. Features may not operate when a required permission is declined or revoked. Android system settings control permissions.

            6. ADVERTISING AND REWARDED FEATURES
            Advertisements may be supplied by Google AdMob. Selected features may be unlocked through rewarded advertisements. The Live Cost Bubble unlock may require completion of two short rewarded advertisements, as disclosed in the unlock screen. Rewarded advertisements do not provide cash, airtime, mobile data or prizes.

            7. PURCHASES AND THIRD-PARTY SERVICES
            If optional purchases are offered, payment processing is handled through Google Play Billing and is subject to Google Play's applicable terms. Third-party services are governed by their own terms and policies.

            8. INTELLECTUAL PROPERTY
            The Application, software, design, content, BQ Sqwad Data Receipt name, BQ Sqwad brand elements and associated logos are owned by or licensed to BQ Sqwad Technologies. No ownership rights are transferred through installation or use. User-generated data receipts may be saved and shared for personal use. Unauthorised copying, modification, redistribution or commercial exploitation of the Application is prohibited, except where applicable law permits otherwise.

            9. AVAILABILITY AND CHANGES
            Features may be updated, changed, suspended or discontinued for security, performance or legal reasons. Device compatibility, network access and third-party service availability may affect operation.

            10. DISCLAIMER
            TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, THE APPLICATION IS PROVIDED "AS IS" AND "AS AVAILABLE", WITHOUT WARRANTIES OR GUARANTEES, WHETHER EXPRESS, IMPLIED OR STATUTORY. The Application does not provide financial, investment, credit or legal advice.

            11. LIMITATION OF LIABILITY
            To the maximum extent permitted by applicable law, BQ Sqwad Technologies shall not be liable for indirect, incidental, special, consequential or punitive loss, or for loss arising from reliance on estimated usage or cost figures, service interruptions, device settings or third-party services. Liability that cannot lawfully be excluded remains unaffected.

            12. GOVERNING LAW
            These Terms are governed by the laws of the Federal Republic of Nigeria. Disputes are subject to the jurisdiction of the competent courts of Nigeria, subject to mandatory consumer protections.

            13. CHANGES TO THESE TERMS
            Revised Terms will be published with an updated date. Continued use after publication of revised Terms constitutes acceptance to the extent permitted by applicable law.

            14. CONTACT
            BQ Sqwad Technologies
            General enquiries: hello@bqsqwadai.com
        """.trimIndent()
        LegalType.COPYRIGHT -> """
            BQ SQWAD TECHNOLOGIES

            COPYRIGHT AND NOTICES

            © 2026 BQ Sqwad Technologies. All rights reserved.

            BQ Sqwad, BQ Sqwad Data Receipt and associated names, marks, designs and logos are owned by or licensed to BQ Sqwad Technologies.

            Android, Google Play and Google AdMob are trademarks of their respective owners. All other third-party names and marks remain the property of their respective owners.

            Contact: hello@bqsqwadai.com
        """.trimIndent()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = { uriHandler.openUri(onlineUrl) }) {
                        Text(stringResource(R.string.view_online))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 24.dp)
        ) {
            Text(
                text = policyText,
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.35
            )
            Spacer(Modifier.height(32.dp))
            CompanyPlate(
                onPrivacy = { if (type != LegalType.PRIVACY) navController.navigate("privacy") },
                onTerms = { if (type != LegalType.TERMS) navController.navigate("terms") },
                onCopyright = { if (type != LegalType.COPYRIGHT) navController.navigate("copyright") }
            )
        }
    }
}

package com.bqsqwad.datareceipt.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bqsqwad.datareceipt.R
import com.bqsqwad.datareceipt.ui.theme.SkyTint
import java.util.Calendar

@Composable
fun CompanyPlate(
    modifier: Modifier = Modifier,
    onPrivacy: () -> Unit = {},
    onTerms: () -> Unit = {},
    onCopyright: () -> Unit = {},
    onMore: () -> Unit = {}
) {
    val year = Calendar.getInstance().get(Calendar.YEAR)
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SkyTint.copy(alpha = 0.6f))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                stringResource(R.string.company_name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                stringResource(R.string.designed_by),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Text(
                stringResource(R.string.all_rights_reserved, year),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                TextButton(onClick = onPrivacy) {
                    Text(stringResource(R.string.privacy_policy), style = MaterialTheme.typography.labelSmall)
                }
                Text(".", color = MaterialTheme.colorScheme.onSurfaceVariant)
                TextButton(onClick = onTerms) {
                    Text(stringResource(R.string.terms_conditions), style = MaterialTheme.typography.labelSmall)
                }
                Text(".", color = MaterialTheme.colorScheme.onSurfaceVariant)
                TextButton(onClick = onCopyright) {
                    Text(stringResource(R.string.copyright_notices), style = MaterialTheme.typography.labelSmall)
                }
            }
            TextButton(onClick = onMore) {
                Text(stringResource(R.string.more_from_company), style = MaterialTheme.typography.labelMedium)
            }
            Text(
                "com.bqsqwad.datareceipt · v1.0.0",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

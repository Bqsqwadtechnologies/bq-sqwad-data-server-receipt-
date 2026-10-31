package com.bqsqwad.datareceipt.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.bqsqwad.datareceipt.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CountryItem(val code: String, val name: String, val currency: String, val currencyName: String)

val COMMON_COUNTRIES = listOf(
    CountryItem("NG", "Nigeria", "NGN", "Nigerian Naira"),
    CountryItem("GH", "Ghana", "GHS", "Ghanaian Cedi"),
    CountryItem("KE", "Kenya", "KES", "Kenyan Shilling"),
    CountryItem("ZA", "South Africa", "ZAR", "South African Rand"),
    CountryItem("US", "United States", "USD", "US Dollar"),
    CountryItem("GB", "United Kingdom", "GBP", "British Pound"),
    CountryItem("IN", "India", "INR", "Indian Rupee"),
    CountryItem("BR", "Brazil", "BRL", "Brazilian Real"),
    CountryItem("FR", "France", "EUR", "Euro"),
    CountryItem("DE", "Germany", "EUR", "Euro")
)

@HiltViewModel
class CountryCurrencyViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val countryCode = settingsRepository.countryCode.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "NG")
    val currencyCode = settingsRepository.currencyCode.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "NGN")

    fun select(country: CountryItem) {
        viewModelScope.launch {
            settingsRepository.setCountryAndCurrency(country.code, country.currency)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCurrencyScreen(navController: NavController) {
    val viewModel: CountryCurrencyViewModel = hiltViewModel()
    val selectedCountry by viewModel.countryCode.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }

    val filtered = COMMON_COUNTRIES.filter {
        it.name.contains(query, ignoreCase = true) || it.currency.contains(query, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Country & Currency") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            LazyColumn {
                items(filtered) { country ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.select(country) }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(country.name, style = MaterialTheme.typography.titleMedium)
                            Text("${country.currency} · ${country.currencyName}", style = MaterialTheme.typography.bodySmall)
                        }
                        if (selectedCountry == country.code) {
                            Icon(Icons.Default.Check, contentDescription = "Selected", tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

// Helper for collectAsStateWithLifecycle without extra import noise in this file
@Composable
private fun <T> kotlinx.coroutines.flow.StateFlow<T>.collectAsStateWithLifecycle(): State<T> {
    return androidx.lifecycle.compose.collectAsStateWithLifecycle(this)
}

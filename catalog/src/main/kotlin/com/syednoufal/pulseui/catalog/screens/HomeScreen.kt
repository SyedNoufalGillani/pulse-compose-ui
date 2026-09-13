package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.components.card.PulseCard
import com.syednoufal.pulseui.components.card.PulseCardVariant
import com.syednoufal.pulseui.components.segmented.PulseSegmentedControl
import com.syednoufal.pulseui.components.switch.PulseSwitch
import com.syednoufal.pulseui.components.topbar.PulseTopBar
import com.syednoufal.pulseui.catalog.navigation.CatalogCategory
import com.syednoufal.pulseui.catalog.navigation.CatalogDestination
import com.syednoufal.pulseui.catalog.theme.CatalogThemeMode
import com.syednoufal.pulseui.catalog.theme.CatalogThemeViewModel

/**
 * The catalog app's landing screen: a live theme switcher (see [CatalogThemeViewModel]) followed
 * by every component/foundation grouped by [CatalogCategory]. Tapping a row navigates to that
 * item's interactive detail screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    themeViewModel: CatalogThemeViewModel,
    onDestinationSelected: (CatalogDestination) -> Unit,
) {
    val uiState by themeViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { PulseTopBar(title = "Pulse Catalog", centered = true) },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                text = "A component library for building consistent, accessible Android experiences.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            PulseCard(variant = PulseCardVariant.Outlined) {
                Text(text = "Appearance", style = MaterialTheme.typography.titleMedium)
                androidx.compose.foundation.layout.Spacer(Modifier.padding(top = 4.dp))
                PulseSegmentedControl(
                    options = listOf("Light", "Dark", "System"),
                    selectedIndex =
                        when (uiState.themeMode) {
                            CatalogThemeMode.Light -> 0
                            CatalogThemeMode.Dark -> 1
                            CatalogThemeMode.System -> 2
                        },
                    onOptionSelected = { index ->
                        themeViewModel.setThemeMode(
                            when (index) {
                                0 -> CatalogThemeMode.Light
                                1 -> CatalogThemeMode.Dark
                                else -> CatalogThemeMode.System
                            },
                        )
                    },
                )
                androidx.compose.foundation.layout.Spacer(Modifier.padding(top = 12.dp))
                PulseSwitch(
                    checked = uiState.dynamicColorEnabled,
                    onCheckedChange = themeViewModel::setDynamicColorEnabled,
                    label = "Dynamic color",
                    supportingText = "Derive the palette from your wallpaper (Android 12+)",
                )
            }

            CatalogCategory.entries.forEach { category ->
                val destinations = CatalogDestination.catalogEntries.filter { it.category == category }
                if (destinations.isNotEmpty()) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = category.label.uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        destinations.forEach { destination ->
                            CatalogRow(
                                title = destination.title,
                                summary = destination.summary,
                                onClick = { onDestinationSelected(destination) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CatalogRow(
    title: String,
    summary: String,
    onClick: () -> Unit,
) {
    PulseCard(variant = PulseCardVariant.Filled, onClick = onClick) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

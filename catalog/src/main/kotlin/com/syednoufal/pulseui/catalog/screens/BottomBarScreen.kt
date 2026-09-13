package com.syednoufal.pulseui.catalog.screens

import androidx.compose.material.icons.filled.Home as FilledHome
import androidx.compose.material.icons.filled.Person as FilledPerson
import androidx.compose.material.icons.filled.Search as FilledSearch
import androidx.compose.material.icons.filled.Settings as FilledSettings
import androidx.compose.material.icons.outlined.Home as OutlinedHome
import androidx.compose.material.icons.outlined.Person as OutlinedPerson
import androidx.compose.material.icons.outlined.Search as OutlinedSearch
import androidx.compose.material.icons.outlined.Settings as OutlinedSettings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.bottombar.PulseBottomBar
import com.syednoufal.pulseui.components.bottombar.PulseBottomBarItem

/** Demonstrates [PulseBottomBar] with four destinations and live selection state. */
@Composable
fun BottomBarScreen(onNavigateBack: () -> Unit) {
    var selected by remember { mutableIntStateOf(0) }
    val items =
        listOf(
            PulseBottomBarItem("Home", OutlinedHome, FilledHome),
            PulseBottomBarItem("Search", OutlinedSearch, FilledSearch),
            PulseBottomBarItem("Profile", OutlinedPerson, FilledPerson),
            PulseBottomBarItem("Settings", OutlinedSettings, FilledSettings),
        )

    CatalogDetailScaffold(title = "Bottom Navigation", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Selection is fully hoisted") {
            Text(
                text = "Selected tab: ${items[selected].label}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            PulseBottomBar(items = items, selectedIndex = selected, onItemSelected = { selected = it })
        }
    }
}

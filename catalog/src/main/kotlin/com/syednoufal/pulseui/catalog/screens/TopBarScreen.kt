package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.topbar.PulseTopBar

/** Demonstrates [PulseTopBar]'s start-aligned and centered layouts with back navigation and actions. */
@Composable
fun TopBarScreen(onNavigateBack: () -> Unit) {
    CatalogDetailScaffold(title = "Top App Bar", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Start-aligned, with back and actions") {
            PulseTopBar(
                title = "Project settings",
                onNavigateBack = {},
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Filled.Search, contentDescription = "Search") }
                    IconButton(onClick = {}) { Icon(Icons.Filled.MoreVert, contentDescription = "More") }
                },
            )
        }
        CatalogSection(title = "Centered, no back button") {
            PulseTopBar(title = "Pulse Catalog", centered = true)
        }
        Text(
            text = "Tap the back arrow above to see the real navigation icon; " +
                "this screen's own top bar (rendered by CatalogDetailScaffold) demonstrates it live.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = androidx.compose.ui.Modifier.padding(top = 4.dp),
        )
    }
}

package com.syednoufal.pulseui.catalog.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.emptystate.PulseEmptyState
import com.syednoufal.pulseui.components.emptystate.PulseEmptyStateDefaults

/** Demonstrates the three [PulseEmptyStateDefaults] presets, each with a working retry action. */
@Composable
fun EmptyStateScreen(onNavigateBack: () -> Unit) {
    var clearedFilters by remember { mutableStateOf(0) }
    var retries by remember { mutableStateOf(0) }

    CatalogDetailScaffold(title = "Empty State", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "No results") {
            PulseEmptyState(
                icon = PulseEmptyStateDefaults.NoResults,
                title = "No results found",
                message = "Try adjusting your filters or search for something else. (cleared $clearedFilters times)",
                actionLabel = "Clear filters",
                onAction = { clearedFilters++ },
            )
        }
        CatalogSection(title = "Offline") {
            PulseEmptyState(
                icon = PulseEmptyStateDefaults.Offline,
                title = "You're offline",
                message = "Check your connection and try again. (retried $retries times)",
                actionLabel = "Retry",
                onAction = { retries++ },
            )
        }
        CatalogSection(title = "No data") {
            PulseEmptyState(
                icon = PulseEmptyStateDefaults.NoData,
                title = "Nothing here yet",
                message = "Items you create will show up here.",
            )
        }
    }
}

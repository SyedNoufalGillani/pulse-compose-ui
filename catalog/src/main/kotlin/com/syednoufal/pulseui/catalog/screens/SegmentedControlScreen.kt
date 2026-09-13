package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.segmented.PulseSegmentedControl

/** Demonstrates [PulseSegmentedControl] with two and three options, showing the current selection. */
@Composable
fun SegmentedControlScreen(onNavigateBack: () -> Unit) {
    var period by remember { mutableIntStateOf(0) }
    var unit by remember { mutableIntStateOf(1) }

    CatalogDetailScaffold(title = "Segmented Control", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Three options") {
            PulseSegmentedControl(
                options = listOf("Day", "Week", "Month"),
                selectedIndex = period,
                onOptionSelected = { period = it },
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "Selected: ${listOf("Day", "Week", "Month")[period]}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        CatalogSection(title = "Two options") {
            PulseSegmentedControl(
                options = listOf("Metric", "Imperial"),
                selectedIndex = unit,
                onOptionSelected = { unit = it },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

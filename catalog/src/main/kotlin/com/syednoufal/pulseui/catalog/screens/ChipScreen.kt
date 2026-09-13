package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.chip.PulseAssistChip
import com.syednoufal.pulseui.components.chip.PulseFilterChip
import com.syednoufal.pulseui.components.chip.PulseInputChip

/** Demonstrates filter, assist, and input chips, each with real hoisted state. */
@Composable
fun ChipScreen(onNavigateBack: () -> Unit) {
    val filters = remember { mutableStateListOf("Nearby" to true, "Open now" to false, "Top rated" to false) }
    var assistTapped by remember { mutableStateOf(0) }
    val tags = remember { mutableStateListOf("Kotlin", "Compose", "Android") }

    CatalogDetailScaffold(title = "Chip", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Filter chips") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                filters.forEachIndexed { index, (label, selected) ->
                    PulseFilterChip(
                        label = label,
                        selected = selected,
                        onSelectedChange = { filters[index] = label to it },
                    )
                }
            }
        }
        CatalogSection(title = "Assist chip") {
            PulseAssistChip(
                label = "Add to calendar ($assistTapped)",
                onClick = { assistTapped++ },
                icon = Icons.Filled.CalendarToday,
            )
        }
        CatalogSection(title = "Input chips") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                tags.forEach { tag ->
                    PulseInputChip(label = tag, onDismiss = { tags.remove(tag) })
                }
            }
        }
    }
}

package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.bottomsheet.PulseBottomSheet
import com.syednoufal.pulseui.components.button.PulseButton
import com.syednoufal.pulseui.components.button.PulseButtonVariant
import com.syednoufal.pulseui.components.chip.PulseAssistChip

/** Demonstrates [PulseBottomSheet] hosting a simple "Share" sheet with a few actions. */
@Composable
fun BottomSheetScreen(onNavigateBack: () -> Unit) {
    var showSheet by remember { mutableStateOf(false) }
    var lastAction by remember { mutableStateOf("No action taken yet") }

    CatalogDetailScaffold(title = "Bottom Sheet", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Modal sheet") {
            PulseButton(
                text = "Share component",
                onClick = { showSheet = true },
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Secondary,
            )
            Text(
                text = lastAction,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }

    if (showSheet) {
        PulseBottomSheet(
            onDismissRequest = { showSheet = false },
            title = "Share PulseButton",
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Export this component's spec to share with your team.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                androidx.compose.foundation.layout.Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    PulseAssistChip(label = "Copy link", onClick = { lastAction = "Link copied"; showSheet = false })
                    PulseAssistChip(label = "Export PNG", onClick = { lastAction = "Exported as PNG"; showSheet = false })
                }
            }
        }
    }
}

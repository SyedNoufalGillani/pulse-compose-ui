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
import com.syednoufal.pulseui.components.card.PulseCard
import com.syednoufal.pulseui.components.card.PulseCardVariant

/** Demonstrates every [PulseCardVariant], including a clickable card that counts taps. */
@Composable
fun CardScreen(onNavigateBack: () -> Unit) {
    var tapCount by remember { mutableIntStateOf(0) }

    CatalogDetailScaffold(title = "Card", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Filled") {
            PulseCard(variant = PulseCardVariant.Filled, modifier = Modifier.fillMaxWidth()) {
                Text("Filled card", style = MaterialTheme.typography.titleMedium)
                Text(
                    "A flat, tonal surface — the default for dense list content.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        CatalogSection(title = "Outlined") {
            PulseCard(variant = PulseCardVariant.Outlined, modifier = Modifier.fillMaxWidth()) {
                Text("Outlined card", style = MaterialTheme.typography.titleMedium)
                Text(
                    "A bordered, transparent-background surface for nested content.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        CatalogSection(title = "Elevated & clickable") {
            PulseCard(
                variant = PulseCardVariant.Elevated,
                modifier = Modifier.fillMaxWidth(),
                onClick = { tapCount++ },
            ) {
                Text("Tap me", style = MaterialTheme.typography.titleMedium)
                Text("Tapped $tapCount time(s)", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

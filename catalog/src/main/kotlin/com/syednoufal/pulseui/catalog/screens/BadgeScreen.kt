package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.badge.PulseBadge
import com.syednoufal.pulseui.components.badge.PulseBadgeTone
import com.syednoufal.pulseui.components.badge.PulseDotBadge

/** Demonstrates every [PulseBadgeTone] and the minimal [PulseDotBadge] indicator. */
@Composable
fun BadgeScreen(onNavigateBack: () -> Unit) {
    CatalogDetailScaffold(title = "Badge", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Tones") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PulseBadge(text = "Neutral", tone = PulseBadgeTone.Neutral)
                PulseBadge(text = "Active", tone = PulseBadgeTone.Success)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                PulseBadge(text = "Pending", tone = PulseBadgeTone.Warning)
                PulseBadge(text = "Beta", tone = PulseBadgeTone.Info)
                PulseBadge(text = "Failed", tone = PulseBadgeTone.Error)
            }
        }
        CatalogSection(title = "Dot indicator") {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                PulseDotBadge()
                Text("Unread messages", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

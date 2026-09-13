package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.avatar.PulseAvatar
import com.syednoufal.pulseui.components.avatar.PulseAvatarSize

/**
 * Demonstrates [PulseAvatar]'s deterministic initials fallback across sizes and several names,
 * showing that the same name always produces the same color/initials.
 */
@Composable
fun AvatarScreen(onNavigateBack: () -> Unit) {
    CatalogDetailScaffold(title = "Avatar", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Sizes") {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                PulseAvatar(name = "Noufal Gillani", size = PulseAvatarSize.Small)
                PulseAvatar(name = "Noufal Gillani", size = PulseAvatarSize.Medium)
                PulseAvatar(name = "Noufal Gillani", size = PulseAvatarSize.Large)
                PulseAvatar(name = "Noufal Gillani", size = PulseAvatarSize.ExtraLarge)
            }
        }
        CatalogSection(title = "Deterministic per-name color") {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("Ada Lovelace", "Grace Hopper", "Alan Turing", "Barbara Liskov", "").forEach { name ->
                    PulseAvatar(name = name, size = PulseAvatarSize.Large)
                }
            }
        }
    }
}

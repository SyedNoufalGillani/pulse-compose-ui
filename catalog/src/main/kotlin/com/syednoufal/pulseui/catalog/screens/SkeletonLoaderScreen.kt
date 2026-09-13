package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.card.PulseCard
import com.syednoufal.pulseui.components.card.PulseCardVariant
import com.syednoufal.pulseui.components.switch.PulseSwitch
import com.syednoufal.pulseui.components.skeleton.PulseSkeletonListItem
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

/**
 * Demonstrates [PulseSkeletonListItem]'s shimmer animation, toggleable against the real
 * (loaded) content it stands in for.
 */
@Composable
fun SkeletonLoaderScreen(onNavigateBack: () -> Unit) {
    var isLoading by remember { mutableStateOf(true) }

    CatalogDetailScaffold(title = "Skeleton Loader", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Toggle loading state") {
            PulseSwitch(checked = isLoading, onCheckedChange = { isLoading = it }, label = "Loading")
            PulseCard(variant = PulseCardVariant.Outlined, modifier = Modifier.fillMaxWidth()) {
                if (isLoading) {
                    repeat(3) { PulseSkeletonListItem() }
                } else {
                    listOf(
                        "Ada Lovelace" to "Left a comment on Button spec",
                        "Grace Hopper" to "Approved the Card redesign",
                        "Alan Turing" to "Requested changes on Chip variants",
                    ).forEach { (name, message) ->
                        Text(text = name, style = MaterialTheme.typography.titleSmall)
                        Text(
                            text = message,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

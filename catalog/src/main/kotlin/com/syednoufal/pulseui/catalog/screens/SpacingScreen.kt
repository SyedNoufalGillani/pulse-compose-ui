package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.theme.PulseThemeTokens

/** Visualizes the 8pt spacing scale and the tonal elevation scale as proportional bars. */
@Composable
fun SpacingScreen(onNavigateBack: () -> Unit) {
    val spacing = PulseThemeTokens.spacing
    val elevation = PulseThemeTokens.elevation

    CatalogDetailScaffold(title = "Spacing & Elevation", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Spacing scale (8pt grid)") {
            SpacingRow("none", spacing.none)
            SpacingRow("xxs", spacing.xxs)
            SpacingRow("xs", spacing.xs)
            SpacingRow("sm", spacing.sm)
            SpacingRow("md", spacing.md)
            SpacingRow("lg", spacing.lg)
            SpacingRow("xl", spacing.xl)
            SpacingRow("xxl", spacing.xxl)
            SpacingRow("xxxl", spacing.xxxl)
        }
        CatalogSection(title = "Elevation scale") {
            ElevationRow("level0", elevation.level0)
            ElevationRow("level1", elevation.level1)
            ElevationRow("level2", elevation.level2)
            ElevationRow("level3", elevation.level3)
            ElevationRow("level4", elevation.level4)
            ElevationRow("level5", elevation.level5)
        }
    }
}

@Composable
private fun SpacingRow(name: String, value: Dp) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.width(56.dp),
        )
        androidx.compose.foundation.layout.Box(
            modifier =
                Modifier
                    .height(16.dp)
                    .width(value.coerceAtLeastVisual())
                    .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraSmall),
        )
        Text(
            text = "  ${value.value.toInt()}dp",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ElevationRow(name: String, value: Dp) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.width(56.dp),
        )
        androidx.compose.material3.Surface(
            tonalElevation = value,
            shadowElevation = value,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.height(32.dp).width(120.dp),
        ) {
            androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center, modifier = Modifier) {
                Text(text = "${value.value.toInt()}dp", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

/** Keeps zero-width spacing bars visible as a hairline instead of disappearing entirely. */
private fun Dp.coerceAtLeastVisual(): Dp = if (this < 2.dp) 2.dp else this

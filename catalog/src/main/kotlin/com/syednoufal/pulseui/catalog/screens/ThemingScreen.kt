package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.theme.PulseThemeTokens

/**
 * Demonstrates every color role Pulse UI exposes: the Material 3 roles driven by
 * [PulseThemeTokens.colors], and the semantic success/warning/info roles from
 * [PulseThemeTokens.extendedColors] that Material 3 doesn't model on its own.
 */
@Composable
fun ThemingScreen(onNavigateBack: () -> Unit) {
    val colors = PulseThemeTokens.colors
    val extended = PulseThemeTokens.extendedColors

    CatalogDetailScaffold(title = "Theming", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Material roles") {
            Text(
                "Toggle Light/Dark/System and dynamic color from the Home screen to see these update live.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            val roleSwatches =
                listOf(
                    "primary" to (colors.primary to colors.onPrimary),
                    "primaryContainer" to (colors.primaryContainer to colors.onPrimaryContainer),
                    "secondary" to (colors.secondary to colors.onSecondary),
                    "secondaryContainer" to (colors.secondaryContainer to colors.onSecondaryContainer),
                    "tertiary" to (colors.tertiary to colors.onTertiary),
                    "tertiaryContainer" to (colors.tertiaryContainer to colors.onTertiaryContainer),
                    "error" to (colors.error to colors.onError),
                    "errorContainer" to (colors.errorContainer to colors.onErrorContainer),
                    "surface" to (colors.surface to colors.onSurface),
                    "surfaceVariant" to (colors.surfaceVariant to colors.onSurfaceVariant),
                )
            ColorSwatchGrid(roleSwatches)
        }

        CatalogSection(title = "Semantic extended colors") {
            Text(
                "Custom roles Pulse UI adds via a CompositionLocal for states Material 3 doesn't cover.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            val extendedSwatches =
                listOf(
                    "success" to (extended.success to extended.onSuccess),
                    "successContainer" to (extended.successContainer to extended.onSuccessContainer),
                    "warning" to (extended.warning to extended.onWarning),
                    "warningContainer" to (extended.warningContainer to extended.onWarningContainer),
                    "info" to (extended.info to extended.onInfo),
                    "infoContainer" to (extended.infoContainer to extended.onInfoContainer),
                )
            ColorSwatchGrid(extendedSwatches)
        }
    }
}

@Composable
private fun ColorSwatchGrid(swatches: List<Pair<String, Pair<Color, Color>>>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(((swatches.size / 2 + 1) * 72).dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(swatches.size) { index ->
            val (name, colorPair) = swatches[index]
            val (background, onColor) = colorPair
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(background, MaterialTheme.shapes.small)
                        .padding(8.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(text = name, color = onColor, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

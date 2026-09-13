package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.theme.PulseThemeTokens

/** Renders every style in the Pulse type scale against its own name, as a live specimen sheet. */
@Composable
fun TypographyScreen(onNavigateBack: () -> Unit) {
    val typography = PulseThemeTokens.typography

    CatalogDetailScaffold(title = "Typography", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Display") {
            Specimen("displayLarge", typography.displayLarge)
            Specimen("displayMedium", typography.displayMedium)
            Specimen("displaySmall", typography.displaySmall)
        }
        CatalogSection(title = "Headline") {
            Specimen("headlineLarge", typography.headlineLarge)
            Specimen("headlineMedium", typography.headlineMedium)
            Specimen("headlineSmall", typography.headlineSmall)
        }
        CatalogSection(title = "Title") {
            Specimen("titleLarge", typography.titleLarge)
            Specimen("titleMedium", typography.titleMedium)
            Specimen("titleSmall", typography.titleSmall)
        }
        CatalogSection(title = "Body") {
            Specimen("bodyLarge", typography.bodyLarge)
            Specimen("bodyMedium", typography.bodyMedium)
            Specimen("bodySmall", typography.bodySmall)
        }
        CatalogSection(title = "Label") {
            Specimen("labelLarge", typography.labelLarge)
            Specimen("labelMedium", typography.labelMedium)
            Specimen("labelSmall", typography.labelSmall)
        }
    }
}

@Composable
private fun Specimen(name: String, style: TextStyle) {
    Column {
        Text(text = "Pulse UI", style = style.copy(fontFamily = typographyFontFamily()))
        Text(
            text = "$name  ·  ${style.fontSize.value.toInt()}sp / ${style.lineHeight.value.toInt()}sp",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun typographyFontFamily() = PulseThemeTokens.typography.fontFamily

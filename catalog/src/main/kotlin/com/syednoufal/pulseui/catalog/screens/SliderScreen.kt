package com.syednoufal.pulseui.catalog.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.slider.PulseSlider
import kotlin.math.roundToInt

/** Demonstrates [PulseSlider] in continuous and stepped configurations. */
@Composable
fun SliderScreen(onNavigateBack: () -> Unit) {
    var volume by remember { mutableFloatStateOf(70f) }
    var textSize by remember { mutableFloatStateOf(3f) }

    CatalogDetailScaffold(title = "Slider", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Continuous") {
            PulseSlider(
                value = volume,
                onValueChange = { volume = it },
                valueRange = 0f..100f,
                label = "Volume",
                valueFormatter = { "${it.roundToInt()}%" },
            )
        }
        CatalogSection(title = "Stepped") {
            PulseSlider(
                value = textSize,
                onValueChange = { textSize = it },
                valueRange = 1f..5f,
                steps = 3,
                label = "Text size",
                valueFormatter = { listOf("XS", "S", "M", "L", "XL")[it.roundToInt() - 1] },
            )
        }
    }
}

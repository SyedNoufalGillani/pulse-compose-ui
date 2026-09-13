package com.syednoufal.pulseui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Elevation scale for Pulse UI surfaces, aligned with Material 3 tonal elevation levels.
 *
 * Rather than reaching for shadows directly, components use these tokens so elevation stays
 * consistent and can be redefined per-brand (e.g. a flatter, shadow-free theme) in one place.
 *
 * @property level0 Resting state for surfaces that sit flush with the background (e.g. a full
 * bleed screen background).
 * @property level1 Cards and other low-emphasis containers.
 * @property level2 Chips, resting buttons, and other subtly raised controls.
 * @property level3 App bars, elevated buttons, and popovers.
 * @property level4 Navigation bars and bottom sheets.
 * @property level5 Modal surfaces such as dialogs — the highest resting elevation in the system.
 */
@Immutable
data class PulseElevation(
    val level0: Dp = 0.dp,
    val level1: Dp = 1.dp,
    val level2: Dp = 3.dp,
    val level3: Dp = 6.dp,
    val level4: Dp = 8.dp,
    val level5: Dp = 12.dp,
)

/** CompositionLocal exposing the current [PulseElevation] scale. Access via [PulseTheme]. */
val LocalPulseElevation = staticCompositionLocalOf { PulseElevation() }

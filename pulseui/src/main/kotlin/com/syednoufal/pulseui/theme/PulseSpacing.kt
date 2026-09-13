package com.syednoufal.pulseui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing scale for Pulse UI, built on an 8pt grid with a single 4dp half-step for fine
 * adjustments (icon-to-text gaps, dense list rows).
 *
 * Prefer these tokens over raw `dp` literals so spacing stays consistent across the design
 * system and can be retuned globally by adjusting [PulseTheme].
 *
 * @property none Zero spacing, used to explicitly opt out of a default gap.
 * @property xxs Half-step (4dp) for the tightest icon/text pairings.
 * @property xs 8dp, the base grid unit.
 * @property sm 12dp, for compact component internal padding.
 * @property md 16dp, the standard content padding used by most screens and cards.
 * @property lg 24dp, for separating distinct sections within a screen.
 * @property xl 32dp, for large section breaks.
 * @property xxl 48dp, for hero spacing and empty-state layouts.
 * @property xxxl 64dp, for the most generous top-level layout gaps.
 */
@Immutable
data class PulseSpacing(
    val none: Dp = 0.dp,
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val xxl: Dp = 48.dp,
    val xxxl: Dp = 64.dp,
)

/** CompositionLocal exposing the current [PulseSpacing] scale. Access via [PulseTheme]. */
val LocalPulseSpacing = staticCompositionLocalOf { PulseSpacing() }

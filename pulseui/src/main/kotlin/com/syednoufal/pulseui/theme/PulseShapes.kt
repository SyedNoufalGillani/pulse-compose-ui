package com.syednoufal.pulseui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Corner radius tokens for Pulse UI, mapped onto [androidx.compose.material3.Shapes] so that
 * every Material 3 component (and every Pulse component built on top of it) picks up a
 * consistent rounding language automatically.
 *
 * The scale intentionally has more steps than the Material 3 default so components like
 * [com.syednoufal.pulseui.components.chip.PulseChip] and
 * [com.syednoufal.pulseui.components.badge.PulseBadge] can opt into a fully pill-shaped corner
 * radius independently of card/dialog rounding.
 */
object PulseShapeTokens {
    val none = RoundedCornerShape(0.dp)
    val extraSmall = RoundedCornerShape(4.dp)
    val small = RoundedCornerShape(8.dp)
    val medium = RoundedCornerShape(12.dp)
    val large = RoundedCornerShape(16.dp)
    val extraLarge = RoundedCornerShape(28.dp)
    val full = RoundedCornerShape(percent = 50)
}

/** The [Shapes] instance Pulse UI feeds into `MaterialTheme` via [PulseTheme]. */
val PulseShapes =
    Shapes(
        extraSmall = PulseShapeTokens.extraSmall,
        small = PulseShapeTokens.small,
        medium = PulseShapeTokens.medium,
        large = PulseShapeTokens.large,
        extraLarge = PulseShapeTokens.extraLarge,
    )

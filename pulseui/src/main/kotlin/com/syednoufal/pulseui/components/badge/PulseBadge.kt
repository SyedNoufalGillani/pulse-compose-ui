package com.syednoufal.pulseui.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseExtendedColors
import com.syednoufal.pulseui.theme.PulseTheme
import com.syednoufal.pulseui.theme.PulseThemeTokens

/**
 * Semantic tone for [PulseBadge], mapped onto [PulseExtendedColors] container/on-container pairs
 * so status meaning (not just decoration) is conveyed consistently across the system.
 */
enum class PulseBadgeTone {
    Neutral,
    Success,
    Warning,
    Info,
    Error,
}

/**
 * A small status/count pill. Use the text overload for labeled statuses ("Active", "3 new") and
 * [PulseDotBadge] as a minimal unlabeled indicator (e.g. an unread marker on a nav icon).
 *
 * @param text Badge label. Keep it short — one or two words, or a number.
 * @param tone Semantic tone; see [PulseBadgeTone].
 */
@Composable
fun PulseBadge(
    text: String,
    modifier: Modifier = Modifier,
    tone: PulseBadgeTone = PulseBadgeTone.Neutral,
) {
    val (container, onContainer) = tone.colors()
    Box(
        modifier =
            modifier
                .background(color = container, shape = MaterialTheme.shapes.small)
                .padding(horizontal = 8.dp, vertical = 2.dp),
    ) {
        Text(text = text, color = onContainer, style = MaterialTheme.typography.labelSmall)
    }
}

/**
 * A minimal dot indicator conveying presence/attention without a label (e.g. an unread badge on
 * a bottom-nav icon). Pair with [androidx.compose.ui.Modifier.align] inside a `Box` to overlay it
 * on another composable's corner.
 */
@Composable
fun PulseDotBadge(
    modifier: Modifier = Modifier,
    tone: PulseBadgeTone = PulseBadgeTone.Error,
    size: androidx.compose.ui.unit.Dp = 8.dp,
) {
    val (container, _) = tone.colors()
    Box(
        modifier =
            modifier
                .size(size)
                .background(color = container, shape = CircleShape),
    )
}

@Composable
private fun PulseBadgeTone.colors(): Pair<Color, Color> {
    val extended = PulseThemeTokens.extendedColors
    val scheme = PulseThemeTokens.colors
    return when (this) {
        PulseBadgeTone.Neutral -> scheme.surfaceVariant to scheme.onSurfaceVariant
        PulseBadgeTone.Success -> extended.successContainer to extended.onSuccessContainer
        PulseBadgeTone.Warning -> extended.warningContainer to extended.onWarningContainer
        PulseBadgeTone.Info -> extended.infoContainer to extended.onInfoContainer
        PulseBadgeTone.Error -> scheme.errorContainer to scheme.onErrorContainer
    }
}

@Preview(name = "Badge – Light", showBackground = true)
@Composable
private fun PulseBadgeLightPreview() {
    PulseTheme(darkTheme = false) { PulseBadgePreviewContent() }
}

@Preview(name = "Badge – Dark", showBackground = true)
@Composable
private fun PulseBadgeDarkPreview() {
    PulseTheme(darkTheme = true) { PulseBadgePreviewContent() }
}

@Composable
private fun PulseBadgePreviewContent() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
    ) {
        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            PulseBadge(text = "Neutral", tone = PulseBadgeTone.Neutral)
            PulseBadge(text = "Active", tone = PulseBadgeTone.Success)
            PulseBadge(text = "Pending", tone = PulseBadgeTone.Warning)
            PulseBadge(text = "Beta", tone = PulseBadgeTone.Info)
            PulseBadge(text = "Failed", tone = PulseBadgeTone.Error)
        }
        Row(
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PulseDotBadge()
            Text("Unread indicator", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

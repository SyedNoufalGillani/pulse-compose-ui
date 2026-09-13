package com.syednoufal.pulseui.components.skeleton

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Applies Pulse UI's shimmer effect to any composable's background — the building block behind
 * [PulseSkeletonBlock] and [PulseSkeletonListItem]. A [Brush.linearGradient] with a highlight band
 * is animated across the surface using an [rememberInfiniteTransition] float, giving the classic
 * "loading placeholder" sheen without any extra draw-order tricks.
 *
 * @param shape Corner shape to clip the shimmer to. Defaults to no clipping (a plain rectangle).
 */
fun Modifier.pulseShimmer(shape: Shape = androidx.compose.ui.graphics.RectangleShape): Modifier =
    composed {
        val baseColor = MaterialTheme.colorScheme.surfaceVariant
        val highlightColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)

        val transition = rememberInfiniteTransition(label = "pulse-shimmer")
        val translateAnim by transition.animateFloat(
            initialValue = -1000f,
            targetValue = 1000f,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(durationMillis = 1200, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                ),
            label = "pulse-shimmer-translate",
        )

        val brush =
            Brush.linearGradient(
                colors = listOf(baseColor, highlightColor, baseColor),
                start = Offset(translateAnim, 0f),
                end = Offset(translateAnim + 400f, 400f),
            )

        this
            .clip(shape)
            .background(brush)
    }

/**
 * A single shimmering placeholder block. Compose several of these (see [PulseSkeletonListItem])
 * to build up loading placeholders that mirror a screen's real content layout.
 *
 * @param modifier Size/shape is normally supplied here, e.g. `Modifier.size(64.dp)` or
 * `Modifier.fillMaxWidth().height(16.dp)`.
 * @param shape Corner rounding for the block.
 */
@Composable
fun PulseSkeletonBlock(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier.pulseShimmer(shape),
    )
}

/**
 * A ready-made list-row skeleton (circular avatar placeholder + two lines of text placeholder),
 * matching the most common loading-state layout in list/feed screens.
 */
@Composable
fun PulseSkeletonListItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        PulseSkeletonBlock(modifier = Modifier.size(48.dp), shape = CircleShape)
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 4.dp),
        ) {
            PulseSkeletonBlock(modifier = Modifier.width(160.dp).height(14.dp))
            PulseSkeletonBlock(modifier = Modifier.width(220.dp).height(12.dp))
        }
    }
}

@Preview(name = "Skeleton – Light", showBackground = true)
@Composable
private fun PulseSkeletonLightPreview() {
    PulseTheme(darkTheme = false) { PulseSkeletonPreviewContent() }
}

@Preview(name = "Skeleton – Dark", showBackground = true)
@Composable
private fun PulseSkeletonDarkPreview() {
    PulseTheme(darkTheme = true) { PulseSkeletonPreviewContent() }
}

@Composable
private fun PulseSkeletonPreviewContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        repeat(3) {
            PulseSkeletonListItem()
        }
    }
}

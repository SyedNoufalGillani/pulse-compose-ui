package com.syednoufal.pulseui.components.rating

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A star rating indicator supporting both interactive input (tap a star to set [rating]) and a
 * read-only display mode with fractional stars (e.g. a 3.5-star average shown as a half-filled
 * icon).
 *
 * @param rating Current rating, in `0f..starCount.toFloat()`. Fractional values (e.g. `3.5f`)
 * render a half-star only when [readOnly] is true — interactive taps always snap to whole stars.
 * @param onRatingChange Invoked with the tapped star's whole-number value. Ignored when [readOnly].
 * @param starCount Total number of stars rendered.
 * @param readOnly When true, disables taps and allows half-star rendering for [rating]; use this
 * for displaying an aggregate/average rating rather than collecting input.
 * @param starSize Size of each star glyph.
 * @param filledColor Tint applied to filled (and half-filled) stars.
 * @param emptyColor Tint applied to empty stars.
 */
@Composable
fun PulseRatingBar(
    rating: Float,
    onRatingChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    readOnly: Boolean = false,
    starSize: Dp = 28.dp,
    filledColor: Color = MaterialTheme.colorScheme.primary,
    emptyColor: Color = MaterialTheme.colorScheme.outlineVariant,
) {
    Row(
        modifier =
            modifier.semantics {
                contentDescription = "Rating: $rating out of $starCount stars"
            },
    ) {
        for (index in 1..starCount) {
            val starValue = index.toFloat()
            val isFilled = rating >= starValue
            val isHalfFilled = !isFilled && rating > starValue - 1f && rating < starValue

            val icon =
                when {
                    isFilled -> Icons.Filled.Star
                    isHalfFilled && readOnly -> Icons.Filled.StarHalf
                    else -> Icons.Outlined.StarOutline
                }

            val interactionSource = remember { MutableInteractionSource() }
            val scale by animateFloatAsState(
                targetValue = if (isFilled || (isHalfFilled && readOnly)) 1f else 0.92f,
                animationSpec = spring(dampingRatio = 0.5f),
                label = "pulse-rating-star-scale",
            )

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isFilled || isHalfFilled) filledColor else emptyColor,
                modifier =
                    Modifier
                        .size(starSize)
                        .padding(horizontal = 2.dp)
                        .graphicsLayer(scaleX = scale, scaleY = scale)
                        .let { base ->
                            if (readOnly) {
                                base
                            } else {
                                base.clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                ) { onRatingChange(index) }
                            }
                        },
            )
        }
    }
}

@Preview(name = "RatingBar – Light", showBackground = true)
@Composable
private fun PulseRatingBarLightPreview() {
    com.syednoufal.pulseui.theme.PulseTheme(darkTheme = false) { PulseRatingBarPreviewContent() }
}

@Preview(name = "RatingBar – Dark", showBackground = true)
@Composable
private fun PulseRatingBarDarkPreview() {
    com.syednoufal.pulseui.theme.PulseTheme(darkTheme = true) { PulseRatingBarPreviewContent() }
}

@Composable
private fun PulseRatingBarPreviewContent() {
    var rating by remember { androidx.compose.runtime.mutableIntStateOf(3) }
    androidx.compose.foundation.layout.Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
    ) {
        PulseRatingBar(rating = rating.toFloat(), onRatingChange = { rating = it })
        PulseRatingBar(rating = 3.5f, onRatingChange = {}, readOnly = true)
    }
}

package com.syednoufal.pulseui.components.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Button as Material3Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Visual treatment for [PulseButton]. Pick the variant that matches the action's importance on
 * screen rather than restyling a single button type — this keeps hierarchy consistent across
 * the app (see the catalog app's Buttons screen for a side-by-side comparison).
 */
enum class PulseButtonVariant {
    /** Highest emphasis — filled, brand-colored. Use once per screen for the primary action. */
    Primary,

    /** Medium emphasis — tonal fill. For secondary actions alongside a [Primary] button. */
    Secondary,

    /** Low emphasis — outlined, transparent background. For optional or dismissive actions. */
    Tertiary,

    /** Reserved for irreversible/destructive actions (delete, remove, sign out). Filled with the error color. */
    Destructive,
}

/**
 * Pulse UI's standard button, covering every emphasis level the design system needs through
 * [PulseButtonVariant] plus a built-in [loading] state so call sites never have to hand-roll a
 * spinner-and-disable pattern around a raw Material button.
 *
 * The label cross-fades with a [CircularProgressIndicator] when [loading] flips, and the button
 * is automatically disabled while loading to prevent duplicate submissions.
 *
 * @param text The button label.
 * @param onClick Invoked on click. Never called while [enabled] is false or [loading] is true.
 * @param modifier Modifier applied to the button's root layout.
 * @param variant Emphasis level; see [PulseButtonVariant].
 * @param enabled Whether the button accepts input. Ignored (treated as false) while [loading].
 * @param loading Shows an inline spinner in place of the label and disables the button.
 * @param leadingIcon Optional icon rendered before the label. Hidden automatically while [loading].
 */
@Composable
fun PulseButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: PulseButtonVariant = PulseButtonVariant.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isEnabled = enabled && !loading

    val content: @Composable RowScope.() -> Unit = {
        AnimatedVisibility(
            visible = loading,
            enter = fadeIn(tween(150)) + expandHorizontally(tween(150)),
            exit = fadeOut(tween(150)) + shrinkHorizontally(tween(150)),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = LocalContentColor.current,
                )
                androidx.compose.foundation.layout.Spacer(Modifier.width(8.dp))
            }
        }
        if (!loading && leadingIcon != null) {
            androidx.compose.material3.Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            androidx.compose.foundation.layout.Spacer(Modifier.width(ButtonDefaults.IconSpacing))
        }
        Text(text = text)
    }

    when (variant) {
        PulseButtonVariant.Primary ->
            Material3Button(
                onClick = onClick,
                modifier = modifier,
                enabled = isEnabled,
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(),
                content = content,
            )

        PulseButtonVariant.Secondary ->
            Material3Button(
                onClick = onClick,
                modifier = modifier,
                enabled = isEnabled,
                interactionSource = interactionSource,
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        disabledContainerColor =
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                        disabledContentColor =
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    ),
                content = content,
            )

        PulseButtonVariant.Tertiary ->
            OutlinedButton(
                onClick = onClick,
                modifier = modifier,
                enabled = isEnabled,
                interactionSource = interactionSource,
                content = content,
            )

        PulseButtonVariant.Destructive ->
            Material3Button(
                onClick = onClick,
                modifier = modifier,
                enabled = isEnabled,
                interactionSource = interactionSource,
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                        disabledContainerColor =
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                        disabledContentColor =
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    ),
                content = content,
            )
    }
}

/**
 * A minimal-emphasis text-only button (no container, no border), for actions like "Skip" or
 * "Learn more" that shouldn't compete visually with a [PulseButton].
 */
@Composable
fun PulseTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    TextButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Text(text = text)
    }
}

@Preview(name = "Buttons – Light", showBackground = true)
@Composable
private fun PulseButtonLightPreview() {
    PulseTheme(darkTheme = false) {
        Row(
            modifier = Modifier.height(400.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PulseButtonPreviewContent()
        }
    }
}

@Preview(name = "Buttons – Dark", showBackground = true)
@Composable
private fun PulseButtonDarkPreview() {
    PulseTheme(darkTheme = true) {
        Row(
            modifier = Modifier.height(400.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PulseButtonPreviewContent()
        }
    }
}

@Composable
private fun PulseButtonPreviewContent() {
    androidx.compose.foundation.layout.Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        PulseButton(text = "Primary", onClick = {})
        PulseButton(text = "Secondary", onClick = {}, variant = PulseButtonVariant.Secondary)
        PulseButton(text = "Tertiary", onClick = {}, variant = PulseButtonVariant.Tertiary)
        PulseButton(text = "Destructive", onClick = {}, variant = PulseButtonVariant.Destructive)
        PulseButton(text = "Loading", onClick = {}, loading = true)
        PulseButton(text = "Disabled", onClick = {}, enabled = false)
        PulseTextButton(text = "Text button", onClick = {})
    }
}

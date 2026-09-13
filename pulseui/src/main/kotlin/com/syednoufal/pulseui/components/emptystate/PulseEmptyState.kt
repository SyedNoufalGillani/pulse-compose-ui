package com.syednoufal.pulseui.components.emptystate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.components.button.PulseButton
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * A full-bleed placeholder for a screen or section with no content, wrong search results, or a
 * failed load — the composable equivalent of a 404 page. Centers an icon, headline, supporting
 * text, and an optional retry/primary action.
 *
 * @param icon Illustration glyph. [PulseEmptyStateDefaults] offers common presets.
 * @param title Short headline (e.g. "No results found").
 * @param message Supporting body text explaining the state or suggesting next steps.
 * @param actionLabel When non-null (with [onAction]), renders a primary button below the message.
 * @param onAction Invoked when [actionLabel] is tapped.
 */
@Composable
fun PulseEmptyState(
    icon: ImageVector,
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        if (actionLabel != null && onAction != null) {
            PulseButton(text = actionLabel, onClick = onAction)
        }
    }
}

/** Common icon presets for [PulseEmptyState], covering the most frequent empty-state scenarios. */
object PulseEmptyStateDefaults {
    val NoResults: ImageVector = Icons.Filled.SearchOff
    val NoData: ImageVector = Icons.Filled.Inbox
    val Offline: ImageVector = Icons.Filled.WifiOff
}

@Preview(name = "EmptyState – Light", showBackground = true)
@Composable
private fun PulseEmptyStateLightPreview() {
    PulseTheme(darkTheme = false) { PulseEmptyStatePreviewContent() }
}

@Preview(name = "EmptyState – Dark", showBackground = true)
@Composable
private fun PulseEmptyStateDarkPreview() {
    PulseTheme(darkTheme = true) { PulseEmptyStatePreviewContent() }
}

@Composable
private fun PulseEmptyStatePreviewContent() {
    PulseEmptyState(
        icon = PulseEmptyStateDefaults.NoResults,
        title = "No results found",
        message = "Try adjusting your filters or search for something else.",
        actionLabel = "Clear filters",
        onAction = {},
    )
}

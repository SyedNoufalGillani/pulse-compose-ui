package com.syednoufal.pulseui.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Visual weight for [PulseCard]. Controls the shadow/tonal treatment applied to
 * [androidx.compose.material3.Card] under the hood.
 */
enum class PulseCardVariant {
    /** A flat, tonal surface with no shadow — the default for content-dense lists. */
    Filled,

    /** A bordered, transparent-background surface — for secondary or nested content. */
    Outlined,

    /** A shadow-elevated surface — for content that should visually float above the screen. */
    Elevated,
}

/**
 * A general-purpose content container used throughout Pulse UI for grouping related content
 * (list rows, summary tiles, settings sections). Provides consistent internal padding and
 * corner rounding via [PulseTheme] shapes so individual screens never have to redeclare them.
 *
 * @param modifier Modifier applied to the card's root layout.
 * @param variant Visual weight; see [PulseCardVariant].
 * @param onClick When non-null, makes the whole card clickable (with ripple) and invokes this on tap.
 * @param contentPadding Padding applied inside the card, around [content].
 * @param content The card body.
 */
@Composable
fun PulseCard(
    modifier: Modifier = Modifier,
    variant: PulseCardVariant = PulseCardVariant.Filled,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable androidx.compose.foundation.layout.ColumnScope.() -> Unit,
) {
    val clickableModifier = if (onClick != null) modifier.clickable(onClick = onClick) else modifier

    when (variant) {
        PulseCardVariant.Filled ->
            Card(
                modifier = clickableModifier,
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            ) {
                Column(modifier = Modifier.padding(contentPadding), content = content)
            }

        PulseCardVariant.Outlined ->
            androidx.compose.material3.OutlinedCard(modifier = clickableModifier) {
                Column(modifier = Modifier.padding(contentPadding), content = content)
            }

        PulseCardVariant.Elevated ->
            androidx.compose.material3.ElevatedCard(modifier = clickableModifier) {
                Column(modifier = Modifier.padding(contentPadding), content = content)
            }
    }
}

@Preview(name = "Card – Light", showBackground = true)
@Composable
private fun PulseCardLightPreview() {
    PulseTheme(darkTheme = false) { PulseCardPreviewContent() }
}

@Preview(name = "Card – Dark", showBackground = true)
@Composable
private fun PulseCardDarkPreview() {
    PulseTheme(darkTheme = true) { PulseCardPreviewContent() }
}

@Composable
private fun PulseCardPreviewContent() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
    ) {
        PulseCard(variant = PulseCardVariant.Filled) {
            Text("Filled card", style = MaterialTheme.typography.titleMedium)
            Text("Used for dense list content.", style = MaterialTheme.typography.bodyMedium)
        }
        PulseCard(variant = PulseCardVariant.Outlined) {
            Text("Outlined card", style = MaterialTheme.typography.titleMedium)
        }
        PulseCard(variant = PulseCardVariant.Elevated, onClick = {}) {
            Row {
                Text("Elevated, clickable card", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

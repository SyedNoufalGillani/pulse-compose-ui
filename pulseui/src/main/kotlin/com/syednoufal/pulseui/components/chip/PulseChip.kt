package com.syednoufal.pulseui.components.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * A togglable filter chip, used for multi-select criteria (e.g. category filters above a list).
 * Shows a checkmark leading icon automatically when [selected].
 *
 * @param label Chip text.
 * @param selected Current selection state, hoisted by the caller.
 * @param onSelectedChange Invoked with the new selection state on tap.
 */
@Composable
fun PulseFilterChip(
    label: String,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    FilterChip(
        selected = selected,
        onClick = { onSelectedChange(!selected) },
        label = { androidx.compose.material3.Text(label) },
        modifier = modifier,
        enabled = enabled,
        leadingIcon =
            if (selected) {
                { Icon(Icons.Filled.Check, contentDescription = null, modifier = Modifier.size(FilterChipDefaults.IconSize)) }
            } else {
                null
            },
    )
}

/**
 * A tap-to-act chip that triggers an immediate action (e.g. "Add to calendar"), as opposed to
 * toggling a selection state like [PulseFilterChip].
 *
 * @param label Chip text.
 * @param onClick Invoked on tap.
 * @param icon Optional leading icon, defaults to a generic "add" glyph.
 */
@Composable
fun PulseAssistChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = Icons.Filled.Add,
    enabled: Boolean = true,
) {
    AssistChip(
        onClick = onClick,
        label = { androidx.compose.material3.Text(label) },
        modifier = modifier,
        enabled = enabled,
        leadingIcon =
            icon?.let {
                { Icon(it, contentDescription = null, modifier = Modifier.size(AssistChipDefaults.IconSize)) }
            },
    )
}

/**
 * A dismissible chip representing a discrete piece of user-entered data (e.g. a selected
 * recipient or tag). Shows a trailing close icon that calls [onDismiss] when tapped.
 *
 * @param label Chip text.
 * @param onDismiss Invoked when the trailing close icon is tapped.
 * @param selected Whether the chip renders in its selected (tonal) state.
 * @param onClick Optional tap handler for the chip body itself, separate from [onDismiss].
 */
@Composable
fun PulseInputChip(
    label: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
) {
    InputChip(
        selected = selected,
        onClick = onClick,
        label = { androidx.compose.material3.Text(label) },
        modifier = modifier,
        enabled = enabled,
        trailingIcon = {
            IconButton(onClick = onDismiss, modifier = Modifier.size(InputChipDefaults.IconSize)) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Remove $label",
                    modifier = Modifier.size(InputChipDefaults.IconSize),
                )
            }
        },
    )
}

@Preview(name = "Chips – Light", showBackground = true)
@Composable
private fun PulseChipLightPreview() {
    PulseTheme(darkTheme = false) { PulseChipPreviewContent() }
}

@Preview(name = "Chips – Dark", showBackground = true)
@Composable
private fun PulseChipDarkPreview() {
    PulseTheme(darkTheme = true) { PulseChipPreviewContent() }
}

@Composable
private fun PulseChipPreviewContent() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        var filterSelected by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(true) }
        PulseFilterChip(label = "Nearby", selected = filterSelected, onSelectedChange = { filterSelected = it })
        PulseAssistChip(label = "Filter", onClick = {}, icon = Icons.Filled.FilterList)
        PulseInputChip(label = "Design", onDismiss = {})
    }
}

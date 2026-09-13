package com.syednoufal.pulseui.components.switch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * A togglable switch with an optional inline label, wrapping Material 3's [Switch]. When [label]
 * is supplied, the whole row (not just the thumb) is clickable, matching common settings-screen
 * expectations.
 *
 * @param checked Current state, hoisted by the caller.
 * @param onCheckedChange Invoked with the new state on toggle.
 * @param label Optional text rendered to the start of the switch.
 * @param supportingText Optional secondary text under [label], for a longer description.
 */
@Composable
fun PulseSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    supportingText: String? = null,
    enabled: Boolean = true,
) {
    if (label == null) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = SwitchDefaults.colors(),
        )
        return
    }

    val interactionSource = remember { MutableInteractionSource() }
    val rowModifier =
        if (enabled) {
            modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) { onCheckedChange(!checked) }
        } else {
            modifier.fillMaxWidth()
        }

    Row(
        modifier = rowModifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange, enabled = enabled)
    }
}

@Preview(name = "Switch – Light", showBackground = true)
@Composable
private fun PulseSwitchLightPreview() {
    PulseTheme(darkTheme = false) { PulseSwitchPreviewContent() }
}

@Preview(name = "Switch – Dark", showBackground = true)
@Composable
private fun PulseSwitchDarkPreview() {
    PulseTheme(darkTheme = true) { PulseSwitchPreviewContent() }
}

@Composable
private fun PulseSwitchPreviewContent() {
    var enabled by remember { androidx.compose.runtime.mutableStateOf(true) }
    Column(modifier = Modifier.padding(16.dp)) {
        PulseSwitch(
            checked = enabled,
            onCheckedChange = { enabled = it },
            label = "Push notifications",
            supportingText = "Get notified about comments and mentions",
        )
    }
}

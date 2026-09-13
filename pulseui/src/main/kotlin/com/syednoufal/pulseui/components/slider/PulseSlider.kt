package com.syednoufal.pulseui.components.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme
import kotlin.math.roundToInt

/**
 * A labeled slider for continuous or stepped numeric input, wrapping Material 3's [Slider] with
 * an optional title and a live formatted value readout so screens don't hand-roll that layout
 * repeatedly (volume controls, price range filters, font-size pickers, etc).
 *
 * @param value Current value, hoisted by the caller.
 * @param onValueChange Invoked continuously while dragging.
 * @param valueRange The allowed value range.
 * @param label Optional title shown above the slider.
 * @param steps Number of discrete steps between the endpoints (0 = continuous), forwarded to [Slider].
 * @param valueFormatter Formats [value] for the trailing readout, e.g. `{ "${it.roundToInt()}%" }`.
 */
@Composable
fun PulseSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    label: String? = null,
    steps: Int = 0,
    enabled: Boolean = true,
    valueFormatter: (Float) -> String = { it.roundToInt().toString() },
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (label != null) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = label, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = valueFormatter(value),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            colors = SliderDefaults.colors(),
        )
    }
}

@Preview(name = "Slider – Light", showBackground = true)
@Composable
private fun PulseSliderLightPreview() {
    PulseTheme(darkTheme = false) { PulseSliderPreviewContent() }
}

@Preview(name = "Slider – Dark", showBackground = true)
@Composable
private fun PulseSliderDarkPreview() {
    PulseTheme(darkTheme = true) { PulseSliderPreviewContent() }
}

@Composable
private fun PulseSliderPreviewContent() {
    var brightness by androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(60f) }
    var quality by androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(2f) }
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        PulseSlider(
            value = brightness,
            onValueChange = { brightness = it },
            valueRange = 0f..100f,
            label = "Brightness",
            valueFormatter = { "${it.roundToInt()}%" },
        )
        PulseSlider(
            value = quality,
            onValueChange = { quality = it },
            valueRange = 1f..5f,
            steps = 3,
            label = "Export quality",
            valueFormatter = { it.roundToInt().toString() },
        )
    }
}

package com.syednoufal.pulseui.components.segmented

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * A single-choice segmented control (e.g. "Day / Week / Month" view switchers), wrapping
 * Material 3's [SingleChoiceSegmentedButtonRow] with a simple `List<String>` + selected-index
 * contract instead of requiring callers to manually index [SegmentedButton] children.
 *
 * @param options Labels for each segment, rendered left to right.
 * @param selectedIndex Index into [options] that is currently active.
 * @param onOptionSelected Invoked with the tapped index.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseSegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = { onOptionSelected(index) },
                shape =
                    SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size,
                    ),
                label = { Text(label) },
            )
        }
    }
}

@Preview(name = "SegmentedControl – Light", showBackground = true)
@Composable
private fun PulseSegmentedControlLightPreview() {
    PulseTheme(darkTheme = false) { PulseSegmentedControlPreviewContent() }
}

@Preview(name = "SegmentedControl – Dark", showBackground = true)
@Composable
private fun PulseSegmentedControlDarkPreview() {
    PulseTheme(darkTheme = true) { PulseSegmentedControlPreviewContent() }
}

@Composable
private fun PulseSegmentedControlPreviewContent() {
    var selected by androidx.compose.runtime.remember { androidx.compose.runtime.mutableIntStateOf(0) }
    PulseSegmentedControl(
        options = listOf("Day", "Week", "Month"),
        selectedIndex = selected,
        onOptionSelected = { selected = it },
        modifier = Modifier.padding(16.dp),
    )
}

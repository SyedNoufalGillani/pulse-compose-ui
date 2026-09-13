package com.syednoufal.pulseui.components.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Pulse UI's modal bottom sheet, wrapping Material 3's [ModalBottomSheet] with a title slot and
 * consistent content padding so call sites don't re-derive the same header layout every time.
 *
 * The sheet is fully hoisted via [sheetState] — drive its visibility from a
 * `remember { mutableStateOf(false) }` in the caller and only compose `PulseBottomSheet` while
 * that flag is true, matching `ModalBottomSheet`'s own show/hide contract.
 *
 * @param onDismissRequest Invoked when the sheet is swiped down, scrimmed away, or animates out.
 * @param title Optional header text shown above [content].
 * @param sheetState Controls expand/partial/hidden state. Defaults to a fresh, fully-expandable state.
 * @param content The sheet body.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp).fillMaxWidth().padding(bottom = 24.dp)) {
            if (title != null) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                androidx.compose.foundation.layout.Spacer(Modifier.padding(top = 8.dp))
            }
            content()
        }
    }
}

// Note: ModalBottomSheet requires a real Activity/window context to measure and animate
// correctly, so unlike this library's other components, PulseBottomSheet is demonstrated
// interactively in the catalog app (BottomSheet screen) rather than via a static @Preview.

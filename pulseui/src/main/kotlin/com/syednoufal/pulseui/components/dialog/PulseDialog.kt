package com.syednoufal.pulseui.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Pulse UI's standard confirmation/alert dialog, wrapping Material 3's [AlertDialog] with a
 * simplified title/body/confirm/dismiss contract covering the vast majority of dialog use cases
 * (delete confirmations, permission rationales, simple alerts).
 *
 * For anything requiring custom body content beyond plain text, compose [AlertDialog] directly.
 *
 * @param title Dialog title.
 * @param message Dialog body text.
 * @param onDismissRequest Invoked when the user taps outside the dialog, presses back, or taps
 * [dismissText] (if provided). Callers are responsible for hiding the dialog in response.
 * @param confirmText Label for the primary (confirm) action.
 * @param onConfirm Invoked when [confirmText] is tapped.
 * @param dismissText When non-null, renders a secondary dismiss button with this label.
 * @param isDestructive Renders [confirmText] in the error color for irreversible actions.
 * @param dismissOnClickOutside Whether tapping outside the dialog triggers [onDismissRequest].
 */
@Composable
fun PulseDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    confirmText: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    dismissText: String? = null,
    isDestructive: Boolean = false,
    dismissOnClickOutside: Boolean = true,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = confirmText,
                    color = if (isDestructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                )
            }
        },
        dismissButton =
            dismissText?.let {
                {
                    TextButton(onClick = onDismissRequest) {
                        Text(it)
                    }
                }
            },
        properties = DialogProperties(dismissOnClickOutside = dismissOnClickOutside),
    )
}

@Preview(name = "Dialog – Light", showBackground = true)
@Composable
private fun PulseDialogLightPreview() {
    PulseTheme(darkTheme = false) {
        PulseDialog(
            title = "Delete project?",
            message = "This action can't be undone. All screens and components in this project will be permanently removed.",
            onDismissRequest = {},
            confirmText = "Delete",
            onConfirm = {},
            dismissText = "Cancel",
            isDestructive = true,
        )
    }
}

@Preview(name = "Dialog – Dark", showBackground = true)
@Composable
private fun PulseDialogDarkPreview() {
    PulseTheme(darkTheme = true) {
        PulseDialog(
            title = "Enable notifications?",
            message = "Get notified when a teammate comments on your design.",
            onDismissRequest = {},
            confirmText = "Enable",
            onConfirm = {},
            dismissText = "Not now",
        )
    }
}

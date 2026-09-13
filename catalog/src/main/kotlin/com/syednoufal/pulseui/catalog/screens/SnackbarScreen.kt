package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.button.PulseButton
import com.syednoufal.pulseui.components.button.PulseButtonVariant
import com.syednoufal.pulseui.components.snackbar.PulseSnackbarTone
import com.syednoufal.pulseui.components.snackbar.rememberPulseSnackbarState

/** Demonstrates [com.syednoufal.pulseui.components.snackbar.PulseSnackbarHost] across every tone, plus an undo action. */
@Composable
fun SnackbarScreen(onNavigateBack: () -> Unit) {
    val snackbarState = rememberPulseSnackbarState()

    CatalogDetailScaffold(
        title = "Snackbar",
        onNavigateBack = onNavigateBack,
        snackbarState = snackbarState,
    ) {
        CatalogSection(title = "Tones") {
            PulseButton(
                text = "Show neutral message",
                onClick = { snackbarState.show("Link copied to clipboard") },
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Tertiary,
            )
            PulseButton(
                text = "Show success message",
                onClick = {
                    snackbarState.show(
                        message = "Changes saved",
                        tone = PulseSnackbarTone.Success,
                        icon = Icons.Filled.CheckCircle,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Secondary,
            )
            PulseButton(
                text = "Show error message with undo",
                onClick = {
                    snackbarState.show(
                        message = "Item deleted",
                        tone = PulseSnackbarTone.Error,
                        icon = Icons.Filled.Error,
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Long,
                        onActionPerformed = {
                            snackbarState.show("Delete undone")
                        },
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Destructive,
            )
        }
    }
}

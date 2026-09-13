package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.button.PulseButton
import com.syednoufal.pulseui.components.button.PulseButtonVariant
import com.syednoufal.pulseui.components.dialog.PulseDialog

/** Demonstrates [PulseDialog] as a destructive confirmation and as a neutral alert. */
@Composable
fun DialogScreen(onNavigateBack: () -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }
    var lastResult by remember { mutableStateOf("No action taken yet") }

    CatalogDetailScaffold(title = "Dialog", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Destructive confirmation") {
            PulseButton(
                text = "Delete project",
                onClick = { showDeleteDialog = true },
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Destructive,
            )
        }
        CatalogSection(title = "Informational alert") {
            PulseButton(
                text = "Enable notifications",
                onClick = { showAlertDialog = true },
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Secondary,
            )
        }
        androidx.compose.material3.Text(lastResult)
    }

    if (showDeleteDialog) {
        PulseDialog(
            title = "Delete project?",
            message = "This action can't be undone. All screens and components will be permanently removed.",
            onDismissRequest = { showDeleteDialog = false },
            confirmText = "Delete",
            onConfirm = {
                lastResult = "Project deleted"
                showDeleteDialog = false
            },
            dismissText = "Cancel",
            isDestructive = true,
        )
    }

    if (showAlertDialog) {
        PulseDialog(
            title = "Enable notifications?",
            message = "Get notified when a teammate comments on your design.",
            onDismissRequest = { showAlertDialog = false },
            confirmText = "Enable",
            onConfirm = {
                lastResult = "Notifications enabled"
                showAlertDialog = false
            },
            dismissText = "Not now",
        )
    }
}

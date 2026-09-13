package com.syednoufal.pulseui.catalog.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.switch.PulseSwitch

/** Demonstrates [PulseSwitch] both as a bare toggle and as a labeled, row-clickable settings item. */
@Composable
fun SwitchScreen(onNavigateBack: () -> Unit) {
    var wifi by remember { mutableStateOf(true) }
    var notifications by remember { mutableStateOf(false) }
    var disabledState by remember { mutableStateOf(true) }

    CatalogDetailScaffold(title = "Switch", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Bare switch") {
            PulseSwitch(checked = wifi, onCheckedChange = { wifi = it })
        }
        CatalogSection(title = "Labeled, row-clickable") {
            PulseSwitch(
                checked = notifications,
                onCheckedChange = { notifications = it },
                label = "Push notifications",
                supportingText = "Get notified about comments and mentions",
            )
        }
        CatalogSection(title = "Disabled") {
            PulseSwitch(
                checked = disabledState,
                onCheckedChange = { disabledState = it },
                label = "Managed by admin",
                enabled = false,
            )
        }
    }
}

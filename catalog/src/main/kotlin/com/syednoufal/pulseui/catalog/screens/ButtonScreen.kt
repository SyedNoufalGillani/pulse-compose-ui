package com.syednoufal.pulseui.catalog.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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
import com.syednoufal.pulseui.components.button.PulseTextButton
import com.syednoufal.pulseui.components.switch.PulseSwitch

/**
 * Interactive playground for [PulseButton]: toggling the "Loading" and "Disabled" switches below
 * drives the same live button through every state, alongside a static reference of every
 * [PulseButtonVariant].
 */
@Composable
fun ButtonScreen(onNavigateBack: () -> Unit) {
    var loading by remember { mutableStateOf(false) }
    var disabled by remember { mutableStateOf(false) }

    CatalogDetailScaffold(title = "Button", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Live state playground") {
            PulseSwitch(checked = loading, onCheckedChange = { loading = it }, label = "Loading")
            PulseSwitch(checked = disabled, onCheckedChange = { disabled = it }, label = "Disabled")
            PulseButton(
                text = "Submit",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                loading = loading,
                enabled = !disabled,
                leadingIcon = Icons.Filled.Send,
            )
        }

        CatalogSection(title = "Variants") {
            PulseButton(text = "Primary", onClick = {}, modifier = Modifier.fillMaxWidth())
            PulseButton(
                text = "Secondary",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Secondary,
            )
            PulseButton(
                text = "Tertiary",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Tertiary,
            )
            PulseButton(
                text = "Destructive",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                variant = PulseButtonVariant.Destructive,
            )
            PulseTextButton(text = "Text button", onClick = {}, modifier = Modifier.fillMaxWidth())
        }
    }
}

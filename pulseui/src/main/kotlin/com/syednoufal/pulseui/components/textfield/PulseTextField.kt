package com.syednoufal.pulseui.components.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Pulse UI's text input, wrapping Material 3's [OutlinedTextField] with the pieces every form
 * field in the catalog app ends up needing: an error state with helper text, optional leading
 * and trailing icon slots, a live character counter, and password masking — all as first-class
 * parameters instead of ad-hoc `trailingIcon`/`supportingText` lambdas at every call site.
 *
 * State is fully hoisted: callers own [value] and update it from [onValueChange], so this
 * composable can be driven by a ViewModel's `StateFlow` or plain `remember { mutableStateOf }`.
 *
 * @param value Current field text.
 * @param onValueChange Invoked with the new text on every edit.
 * @param modifier Modifier applied to the field's root layout.
 * @param label Floating label text.
 * @param placeholder Optional placeholder shown when [value] is empty and the field is focused.
 * @param helperText Supporting text shown below the field. Replaced by [errorText] when [isError] is true.
 * @param isError Whether to render the field and [errorText] in the error color.
 * @param errorText Message shown below the field while [isError] is true.
 * @param leadingIcon Optional icon at the start of the field.
 * @param trailingIcon Optional icon at the end of the field, overridden by the built-in clear/visibility
 * icon when [isPassword] is true.
 * @param onTrailingIconClick Click handler for [trailingIcon]. Ignored when [isPassword] is true.
 * @param maxLength When set, renders a "current/max" counter under the field and blocks input beyond it.
 * @param isPassword Masks input and swaps in a show/hide toggle as the trailing icon.
 * @param singleLine Whether the field collapses to one line. Defaults to true, matching most form fields.
 * @param enabled Whether the field accepts input.
 * @param keyboardType Keyboard/input type hint, e.g. [KeyboardType.Email].
 */
@Composable
fun PulseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    helperText: String? = null,
    isError: Boolean = false,
    errorText: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    maxLength: Int? = null,
    isPassword: Boolean = false,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    var passwordVisible by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (maxLength == null || newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = singleLine,
            isError = isError,
            label = label?.let { { Text(it) } },
            placeholder = placeholder?.let { { Text(it) } },
            leadingIcon =
                leadingIcon?.let {
                    { Icon(imageVector = it, contentDescription = null) }
                },
            trailingIcon = {
                when {
                    isPassword -> {
                        val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                        }
                    }
                    trailingIcon != null -> {
                        if (onTrailingIconClick != null) {
                            IconButton(onClick = onTrailingIconClick) {
                                Icon(imageVector = trailingIcon, contentDescription = null)
                            }
                        } else {
                            Icon(imageVector = trailingIcon, contentDescription = null)
                        }
                    }
                }
            },
            visualTransformation =
                when {
                    isPassword && !passwordVisible -> PasswordVisualTransformation()
                    else -> VisualTransformation.None
                },
            keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else keyboardType),
            colors = OutlinedTextFieldDefaults.colors(),
        )

        val showFooter = (isError && errorText != null) || (!isError && helperText != null) || maxLength != null
        if (showFooter) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                val footerColor =
                    if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                val footerText =
                    when {
                        isError && errorText != null -> errorText
                        !isError && helperText != null -> helperText
                        else -> ""
                    }
                Text(
                    text = footerText,
                    style = MaterialTheme.typography.bodySmall,
                    color = footerColor,
                    modifier = Modifier.weight(1f, fill = false),
                )
                if (maxLength != null) {
                    Text(
                        text = "${value.length}/$maxLength",
                        style = MaterialTheme.typography.bodySmall,
                        color = LocalContentColor.current.copy(alpha = 0.6f),
                        fontStyle = FontStyle.Normal,
                    )
                }
            }
        }
    }
}

@Preview(name = "TextField – Light", showBackground = true)
@Composable
private fun PulseTextFieldLightPreview() {
    PulseTheme(darkTheme = false) { PulseTextFieldPreviewContent() }
}

@Preview(name = "TextField – Dark", showBackground = true)
@Composable
private fun PulseTextFieldDarkPreview() {
    PulseTheme(darkTheme = true) { PulseTextFieldPreviewContent() }
}

@Composable
private fun PulseTextFieldPreviewContent() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
    ) {
        PulseTextField(
            value = "hello@pulse.dev",
            onValueChange = {},
            label = "Email",
            leadingIcon = Icons.Filled.Email,
        )
        PulseTextField(
            value = "not-an-email",
            onValueChange = {},
            label = "Email",
            isError = true,
            errorText = "Enter a valid email address",
        )
        PulseTextField(
            value = "Bio text",
            onValueChange = {},
            label = "Bio",
            maxLength = 80,
        )
        PulseTextField(
            value = "supersecret",
            onValueChange = {},
            label = "Password",
            isPassword = true,
        )
    }
}

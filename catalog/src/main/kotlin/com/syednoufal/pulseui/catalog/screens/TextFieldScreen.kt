package com.syednoufal.pulseui.catalog.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.textfield.PulseTextField

/** Demonstrates [PulseTextField]'s error state, icon slots, character counter, and password masking. */
@Composable
fun TextFieldScreen(onNavigateBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("Senior Android Engineer building delightful UI.") }
    var password by remember { mutableStateOf("") }
    var search by remember { mutableStateOf("") }

    val emailIsError = email.isNotEmpty() && !email.contains("@")

    CatalogDetailScaffold(title = "Text Field", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Validation") {
            PulseTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "you@example.com",
                leadingIcon = Icons.Filled.Email,
                isError = emailIsError,
                errorText = "Enter a valid email address",
                helperText = "We'll only use this for account recovery",
            )
        }

        CatalogSection(title = "Character counter") {
            PulseTextField(
                value = bio,
                onValueChange = { bio = it },
                label = "Bio",
                maxLength = 80,
                singleLine = false,
            )
        }

        CatalogSection(title = "Password") {
            PulseTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isPassword = true,
                helperText = "At least 8 characters",
            )
        }

        CatalogSection(title = "Trailing action icon") {
            PulseTextField(
                value = search,
                onValueChange = { search = it },
                label = "Search components",
                trailingIcon = Icons.Filled.Search,
                onTrailingIconClick = { search = "" },
            )
        }
    }
}

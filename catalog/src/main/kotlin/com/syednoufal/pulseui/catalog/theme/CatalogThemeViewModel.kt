package com.syednoufal.pulseui.catalog.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/** User-selectable theme mode for the catalog app, independent of the device's system setting. */
enum class CatalogThemeMode {
    Light,
    Dark,
    System,
}

/** Immutable snapshot of the catalog app's theme preferences, exposed via [CatalogThemeViewModel.uiState]. */
data class CatalogThemeUiState(
    val themeMode: CatalogThemeMode = CatalogThemeMode.System,
    val dynamicColorEnabled: Boolean = true,
)

/**
 * Holds the catalog app's theme/dynamic-color preferences in memory for the lifetime of the
 * process. A real production app would persist this via DataStore; the in-memory `StateFlow`
 * here is intentionally sufficient for a demo whose purpose is showcasing [PulseTheme]'s
 * configuration surface, not preference persistence.
 */
class CatalogThemeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CatalogThemeUiState())
    val uiState: StateFlow<CatalogThemeUiState> = _uiState.asStateFlow()

    fun setThemeMode(mode: CatalogThemeMode) {
        _uiState.update { it.copy(themeMode = mode) }
    }

    fun setDynamicColorEnabled(enabled: Boolean) {
        _uiState.update { it.copy(dynamicColorEnabled = enabled) }
    }
}

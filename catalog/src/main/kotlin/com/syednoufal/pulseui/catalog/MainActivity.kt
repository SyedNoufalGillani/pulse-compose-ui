package com.syednoufal.pulseui.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.syednoufal.pulseui.catalog.navigation.PulseCatalogNavHost
import com.syednoufal.pulseui.catalog.theme.CatalogThemeMode
import com.syednoufal.pulseui.catalog.theme.CatalogThemeViewModel
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Single-activity host for the catalog app. All navigation happens within Compose via
 * [PulseCatalogNavHost]; this activity's only responsibilities are edge-to-edge setup and wiring
 * [CatalogThemeViewModel]'s state into [PulseTheme].
 */
class MainActivity : ComponentActivity() {
    private val themeViewModel: CatalogThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by themeViewModel.uiState.collectAsState()

            val darkTheme =
                when (uiState.themeMode) {
                    CatalogThemeMode.Light -> false
                    CatalogThemeMode.Dark -> true
                    CatalogThemeMode.System -> isSystemInDarkTheme()
                }

            PulseTheme(
                darkTheme = darkTheme,
                dynamicColor = uiState.dynamicColorEnabled,
            ) {
                PulseCatalogNavHost(themeViewModel = themeViewModel)
            }
        }
    }
}

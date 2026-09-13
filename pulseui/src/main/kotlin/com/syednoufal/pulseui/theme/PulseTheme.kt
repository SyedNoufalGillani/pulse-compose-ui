package com.syednoufal.pulseui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

/**
 * Root theming entry point for Pulse UI. Wrap any screen (or your whole app) in [PulseTheme] to
 * make [PulseTheme.colors] (Material 3), [PulseTheme.extendedColors], [PulseTheme.typography],
 * [PulseTheme.spacing], [PulseTheme.elevation] and [PulseTheme.shapes] available, and to apply
 * them to `MaterialTheme` so every stock Material 3 component and every Pulse component matches.
 *
 * ```
 * PulseTheme {
 *     PulseButton(text = "Continue", onClick = { ... })
 * }
 * ```
 *
 * @param darkTheme Whether to render the dark palette. Defaults to the system setting; pass an
 * explicit value to drive a manual light/dark toggle (see the catalog app's theme switcher).
 * @param dynamicColor Whether to derive colors from the user's Android 12+ wallpaper via
 * `dynamicLightColorScheme`/`dynamicDarkColorScheme`. Ignored below API 31, where Pulse always
 * falls back to the hand-tuned [PulseLightColorScheme]/[PulseDarkColorScheme] brand palette.
 * @param typography Override the type scale. Defaults to [PulseTypography]'s system-font ramp.
 * @param spacing Override the 8pt spacing scale. Defaults to [PulseSpacing].
 * @param elevation Override the elevation scale. Defaults to [PulseElevation].
 * @param content The themed content.
 */
@Composable
fun PulseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    typography: PulseTypography = PulseTypography(),
    spacing: PulseSpacing = PulseSpacing(),
    elevation: PulseElevation = PulseElevation(),
    content: @Composable () -> Unit,
) {
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val context = LocalContext.current

    val colorScheme =
        when {
            dynamicColor && supportsDynamicColor && darkTheme -> dynamicDarkColorScheme(context)
            dynamicColor && supportsDynamicColor && !darkTheme -> dynamicLightColorScheme(context)
            darkTheme -> PulseDarkColorScheme
            else -> PulseLightColorScheme
        }

    // Dynamic color schemes don't carry Pulse's semantic success/warning/info roles, so the
    // hand-tuned extended palette is always used regardless of the dynamicColor switch.
    val extendedColors = if (darkTheme) PulseExtendedColorsDark else PulseExtendedColorsLight

    CompositionLocalProvider(
        LocalPulseExtendedColors provides extendedColors,
        LocalPulseTypography provides typography,
        LocalPulseSpacing provides spacing,
        LocalPulseElevation provides elevation,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography.toMaterialTypography(),
            shapes = PulseShapes,
            content = content,
        )
    }
}

/**
 * Namespaced accessors for the tokens [PulseTheme] provides, mirroring `MaterialTheme.colorScheme`
 * / `MaterialTheme.typography` ergonomics. Always call these from within a [PulseTheme] block.
 */
object PulseThemeTokens {
    val colors: androidx.compose.material3.ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val extendedColors: PulseExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPulseExtendedColors.current

    val typography: PulseTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalPulseTypography.current

    val spacing: PulseSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalPulseSpacing.current

    val elevation: PulseElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalPulseElevation.current
}

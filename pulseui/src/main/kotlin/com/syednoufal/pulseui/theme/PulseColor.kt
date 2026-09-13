package com.syednoufal.pulseui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Brand seed palette. These are the source-of-truth hand-picked brand colors; the
// light/dark ColorSchemes below derive tonal roles from them, and the dynamic-color path
// (Android 12+) overrides them with a wallpaper-derived palette in PulseTheme.

internal val PulsePrimaryLight = Color(0xFF4C5FD7)
internal val PulseOnPrimaryLight = Color(0xFFFFFFFF)
internal val PulsePrimaryContainerLight = Color(0xFFE0E1FF)
internal val PulseOnPrimaryContainerLight = Color(0xFF00105C)

internal val PulseSecondaryLight = Color(0xFF5B5D72)
internal val PulseOnSecondaryLight = Color(0xFFFFFFFF)
internal val PulseSecondaryContainerLight = Color(0xFFE0E1F9)
internal val PulseOnSecondaryContainerLight = Color(0xFF181A2C)

internal val PulseTertiaryLight = Color(0xFF77536D)
internal val PulseOnTertiaryLight = Color(0xFFFFFFFF)
internal val PulseTertiaryContainerLight = Color(0xFFFFD7EE)
internal val PulseOnTertiaryContainerLight = Color(0xFF2D1228)

internal val PulseErrorLight = Color(0xFFBA1A1A)
internal val PulseOnErrorLight = Color(0xFFFFFFFF)
internal val PulseErrorContainerLight = Color(0xFFFFDAD6)
internal val PulseOnErrorContainerLight = Color(0xFF410002)

internal val PulseBackgroundLight = Color(0xFFFFFBFF)
internal val PulseOnBackgroundLight = Color(0xFF1B1B1F)
internal val PulseSurfaceLight = Color(0xFFFFFBFF)
internal val PulseOnSurfaceLight = Color(0xFF1B1B1F)
internal val PulseSurfaceVariantLight = Color(0xFFE4E1EC)
internal val PulseOnSurfaceVariantLight = Color(0xFF46464F)
internal val PulseOutlineLight = Color(0xFF767680)

internal val PulsePrimaryDark = Color(0xFFC0C6FF)
internal val PulseOnPrimaryDark = Color(0xFF1B2593)
internal val PulsePrimaryContainerDark = Color(0xFF333EAA)
internal val PulseOnPrimaryContainerDark = Color(0xFFE0E1FF)

internal val PulseSecondaryDark = Color(0xFFC4C5DD)
internal val PulseOnSecondaryDark = Color(0xFF2D2F42)
internal val PulseSecondaryContainerDark = Color(0xFF434559)
internal val PulseOnSecondaryContainerDark = Color(0xFFE0E1F9)

internal val PulseTertiaryDark = Color(0xFFE7B9D4)
internal val PulseOnTertiaryDark = Color(0xFF45263E)
internal val PulseTertiaryContainerDark = Color(0xFF5D3C55)
internal val PulseOnTertiaryContainerDark = Color(0xFFFFD7EE)

internal val PulseErrorDark = Color(0xFFFFB4AB)
internal val PulseOnErrorDark = Color(0xFF690005)
internal val PulseErrorContainerDark = Color(0xFF93000A)
internal val PulseOnErrorContainerDark = Color(0xFFFFDAD6)

internal val PulseBackgroundDark = Color(0xFF1B1B1F)
internal val PulseOnBackgroundDark = Color(0xFFE4E1E6)
internal val PulseSurfaceDark = Color(0xFF1B1B1F)
internal val PulseOnSurfaceDark = Color(0xFFE4E1E6)
internal val PulseSurfaceVariantDark = Color(0xFF46464F)
internal val PulseOnSurfaceVariantDark = Color(0xFFC7C5D0)
internal val PulseOutlineDark = Color(0xFF90909A)

/** The light [androidx.compose.material3.ColorScheme] used when dynamic color is unavailable or disabled. */
val PulseLightColorScheme =
    lightColorScheme(
        primary = PulsePrimaryLight,
        onPrimary = PulseOnPrimaryLight,
        primaryContainer = PulsePrimaryContainerLight,
        onPrimaryContainer = PulseOnPrimaryContainerLight,
        secondary = PulseSecondaryLight,
        onSecondary = PulseOnSecondaryLight,
        secondaryContainer = PulseSecondaryContainerLight,
        onSecondaryContainer = PulseOnSecondaryContainerLight,
        tertiary = PulseTertiaryLight,
        onTertiary = PulseOnTertiaryLight,
        tertiaryContainer = PulseTertiaryContainerLight,
        onTertiaryContainer = PulseOnTertiaryContainerLight,
        error = PulseErrorLight,
        onError = PulseOnErrorLight,
        errorContainer = PulseErrorContainerLight,
        onErrorContainer = PulseOnErrorContainerLight,
        background = PulseBackgroundLight,
        onBackground = PulseOnBackgroundLight,
        surface = PulseSurfaceLight,
        onSurface = PulseOnSurfaceLight,
        surfaceVariant = PulseSurfaceVariantLight,
        onSurfaceVariant = PulseOnSurfaceVariantLight,
        outline = PulseOutlineLight,
    )

/** The dark [androidx.compose.material3.ColorScheme] used when dynamic color is unavailable or disabled. */
val PulseDarkColorScheme =
    darkColorScheme(
        primary = PulsePrimaryDark,
        onPrimary = PulseOnPrimaryDark,
        primaryContainer = PulsePrimaryContainerDark,
        onPrimaryContainer = PulseOnPrimaryContainerDark,
        secondary = PulseSecondaryDark,
        onSecondary = PulseOnSecondaryDark,
        secondaryContainer = PulseSecondaryContainerDark,
        onSecondaryContainer = PulseOnSecondaryContainerDark,
        tertiary = PulseTertiaryDark,
        onTertiary = PulseOnTertiaryDark,
        tertiaryContainer = PulseTertiaryContainerDark,
        onTertiaryContainer = PulseOnTertiaryContainerDark,
        error = PulseErrorDark,
        onError = PulseOnErrorDark,
        errorContainer = PulseErrorContainerDark,
        onErrorContainer = PulseOnErrorContainerDark,
        background = PulseBackgroundDark,
        onBackground = PulseOnBackgroundDark,
        surface = PulseSurfaceDark,
        onSurface = PulseOnSurfaceDark,
        surfaceVariant = PulseSurfaceVariantDark,
        onSurfaceVariant = PulseOnSurfaceVariantDark,
        outline = PulseOutlineDark,
    )

/**
 * Semantic status colors that Material 3's default [androidx.compose.material3.ColorScheme]
 * doesn't model (it only has `error`). Components such as [com.syednoufal.pulseui.components.badge.PulseBadge]
 * and inline validation states read these instead of hardcoding green/amber/blue.
 *
 * Each role ships an "on" counterpart for text/icon content and a "Container"/"OnContainer"
 * pair for lower-emphasis surfaces, mirroring Material 3's own container convention.
 */
@Immutable
data class PulseExtendedColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,
)

internal val PulseExtendedColorsLight =
    PulseExtendedColors(
        success = Color(0xFF2E7D32),
        onSuccess = Color(0xFFFFFFFF),
        successContainer = Color(0xFFD7F2D6),
        onSuccessContainer = Color(0xFF0A2A0C),
        warning = Color(0xFF8A5300),
        onWarning = Color(0xFFFFFFFF),
        warningContainer = Color(0xFFFFDDB0),
        onWarningContainer = Color(0xFF2B1700),
        info = Color(0xFF0061A4),
        onInfo = Color(0xFFFFFFFF),
        infoContainer = Color(0xFFD1E4FF),
        onInfoContainer = Color(0xFF001D36),
    )

internal val PulseExtendedColorsDark =
    PulseExtendedColors(
        success = Color(0xFF9BD99A),
        onSuccess = Color(0xFF0A3A0D),
        successContainer = Color(0xFF1B4E1F),
        onSuccessContainer = Color(0xFFD7F2D6),
        warning = Color(0xFFFFB870),
        onWarning = Color(0xFF472A00),
        warningContainer = Color(0xFF663D00),
        onWarningContainer = Color(0xFFFFDDB0),
        info = Color(0xFF9CCAFF),
        onInfo = Color(0xFF003258),
        infoContainer = Color(0xFF00497D),
        onInfoContainer = Color(0xFFD1E4FF),
    )

/**
 * CompositionLocal exposing the current [PulseExtendedColors]. Always sourced from [PulseTheme];
 * accessing it outside a `PulseTheme` block returns the light defaults with a lint-visible
 * fallback rather than crashing, so previews degrade gracefully.
 */
val LocalPulseExtendedColors = staticCompositionLocalOf { PulseExtendedColorsLight }

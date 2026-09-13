package com.syednoufal.pulseui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * The full Pulse UI type scale, expressed as the system [FontFamily] with Material 3's type
 * ramp for size/weight/line-height. Swap [fontFamily] for a bundled variable font (e.g. an
 * `androidx.compose.ui.text.font.Font` resource) to reskin every component at once without
 * touching individual composables.
 */
@Immutable
data class PulseTypography(
    val fontFamily: FontFamily = FontFamily.Default,
    val displayLarge: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = (-0.25).sp),
    val displayMedium: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 45.sp, lineHeight = 52.sp, letterSpacing = 0.sp),
    val displaySmall: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 36.sp, lineHeight = 44.sp, letterSpacing = 0.sp),
    val headlineLarge: TextStyle =
        TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp),
    val headlineMedium: TextStyle =
        TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 28.sp, lineHeight = 36.sp, letterSpacing = 0.sp),
    val headlineSmall: TextStyle =
        TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp, lineHeight = 32.sp, letterSpacing = 0.sp),
    val titleLarge: TextStyle =
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp),
    val titleMedium: TextStyle =
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.15.sp),
    val titleSmall: TextStyle =
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
    val bodyLarge: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp, letterSpacing = 0.5.sp),
    val bodyMedium: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.25.sp),
    val bodySmall: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.4.sp),
    val labelLarge: TextStyle =
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp),
    val labelMedium: TextStyle =
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp),
    val labelSmall: TextStyle =
        TextStyle(fontWeight = FontWeight.Medium, fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp),
    /** Monospaced style used for [com.syednoufal.pulseui.components.textfield.PulseTextField] counters and code snippets. */
    val caption: TextStyle =
        TextStyle(fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 0.4.sp),
) {
    /** Converts this scale into a Material 3 [Typography], applying [fontFamily] to every style. */
    fun toMaterialTypography(): Typography =
        Typography(
            displayLarge = displayLarge.copy(fontFamily = fontFamily),
            displayMedium = displayMedium.copy(fontFamily = fontFamily),
            displaySmall = displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = titleLarge.copy(fontFamily = fontFamily),
            titleMedium = titleMedium.copy(fontFamily = fontFamily),
            titleSmall = titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = bodySmall.copy(fontFamily = fontFamily),
            labelLarge = labelLarge.copy(fontFamily = fontFamily),
            labelMedium = labelMedium.copy(fontFamily = fontFamily),
            labelSmall = labelSmall.copy(fontFamily = fontFamily),
        )
}

/** CompositionLocal exposing the current [PulseTypography] scale. Access via [PulseTheme]. */
val LocalPulseTypography = staticCompositionLocalOf { PulseTypography() }

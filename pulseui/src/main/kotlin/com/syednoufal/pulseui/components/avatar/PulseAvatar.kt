package com.syednoufal.pulseui.components.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syednoufal.pulseui.theme.PulseTheme
import kotlin.math.absoluteValue

/** Fixed size steps for [PulseAvatar], matching common list-row / profile-header contexts. */
enum class PulseAvatarSize(internal val dp: Dp, internal val fontSizeSp: Int) {
    Small(32.dp, 12),
    Medium(40.dp, 14),
    Large(56.dp, 18),
    ExtraLarge(96.dp, 32),
}

/**
 * A circular user/entity avatar. Renders [painter] when provided (e.g. a loaded profile photo);
 * otherwise falls back to computed initials on a deterministic background color derived from
 * [name], so the same person always gets the same color even without a photo.
 *
 * @param name Full display name, used both to derive initials and to seed the fallback color.
 * Pass an empty string to fall back to a generic "?" glyph.
 * @param painter Optional image content (from `rememberAsyncImagePainter`, a resource, etc). When
 * null, the initials fallback is shown.
 * @param size Fixed size preset; see [PulseAvatarSize].
 */
@Composable
fun PulseAvatar(
    name: String,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    size: PulseAvatarSize = PulseAvatarSize.Medium,
) {
    val initials = remember(name) { initialsFor(name) }
    val backgroundColor = remember(name) { colorFor(name) }

    Box(
        modifier =
            modifier
                .size(size.dp)
                .background(color = if (painter != null) Color.Transparent else backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        if (painter != null) {
            androidx.compose.foundation.Image(
                painter = painter,
                contentDescription = name,
                modifier = Modifier.size(size.dp),
                contentScale = ContentScale.Crop,
            )
        } else {
            Text(
                text = initials,
                color = Color.White,
                fontSize = size.fontSizeSp.sp,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

/**
 * Derives up to two initials from [name]: the first letter of the first and last "words".
 * A single-word name yields one initial; a blank name yields "?".
 */
internal fun initialsFor(name: String): String {
    val parts = name.trim().split(Regex("\\s+")).filter { it.isNotBlank() }
    return when {
        parts.isEmpty() -> "?"
        parts.size == 1 -> parts.first().take(1).uppercase()
        else -> (parts.first().take(1) + parts.last().take(1)).uppercase()
    }
}

/** A small, fixed palette so avatar colors stay legible with white initials text in both themes. */
private val avatarPalette =
    listOf(
        Color(0xFF4C5FD7),
        Color(0xFF2E7D6B),
        Color(0xFFB25E0F),
        Color(0xFF8A4FB2),
        Color(0xFFC2185B),
        Color(0xFF00695C),
        Color(0xFF5D4037),
        Color(0xFF455A64),
    )

/** Deterministically maps [name] onto [avatarPalette] so a given name always renders the same color. */
internal fun colorFor(name: String): Color {
    if (name.isBlank()) return avatarPalette.last()
    val index = name.hashCode().absoluteValue % avatarPalette.size
    return avatarPalette[index]
}

@Preview(name = "Avatar – Light", showBackground = true)
@Composable
private fun PulseAvatarLightPreview() {
    PulseTheme(darkTheme = false) { PulseAvatarPreviewContent() }
}

@Preview(name = "Avatar – Dark", showBackground = true)
@Composable
private fun PulseAvatarDarkPreview() {
    PulseTheme(darkTheme = true) { PulseAvatarPreviewContent() }
}

@Composable
private fun PulseAvatarPreviewContent() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PulseAvatar(name = "Noufal Gillani", size = PulseAvatarSize.Small)
        PulseAvatar(name = "Ada Lovelace", size = PulseAvatarSize.Medium)
        PulseAvatar(name = "Grace Hopper", size = PulseAvatarSize.Large)
        PulseAvatar(name = "", size = PulseAvatarSize.ExtraLarge)
    }
}

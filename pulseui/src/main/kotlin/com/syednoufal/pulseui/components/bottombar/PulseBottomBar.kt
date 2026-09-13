package com.syednoufal.pulseui.components.bottombar

import androidx.compose.material.icons.filled.Home as FilledHome
import androidx.compose.material.icons.filled.Person as FilledPerson
import androidx.compose.material.icons.filled.Search as FilledSearch
import androidx.compose.material.icons.outlined.Home as OutlinedHome
import androidx.compose.material.icons.outlined.Person as OutlinedPerson
import androidx.compose.material.icons.outlined.Search as OutlinedSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * One destination in a [PulseBottomBar].
 *
 * @property label Text shown under the icon.
 * @property icon Icon shown when this item is not selected.
 * @property selectedIcon Icon shown when this item is selected. Defaults to [icon] if a
 * dedicated "filled" variant isn't available.
 */
@Immutable
data class PulseBottomBarItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon,
)

/**
 * Pulse UI's primary bottom navigation surface, wrapping Material 3's [NavigationBar]. Selection
 * is fully hoisted: pass the currently selected index and react to taps via [onItemSelected] —
 * this composable never owns navigation state itself, so it composes cleanly with
 * Navigation-Compose's back stack as the source of truth.
 *
 * @param items Destinations to render, in order.
 * @param selectedIndex Index into [items] that is currently active.
 * @param onItemSelected Invoked with the tapped index.
 */
@Composable
fun PulseBottomBar(
    items: List<PulseBottomBarItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            val selected = index == selectedIndex
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.icon,
                        contentDescription = item.label,
                    )
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(),
            )
        }
    }
}

@Preview(name = "BottomBar – Light", showBackground = true)
@Composable
private fun PulseBottomBarLightPreview() {
    PulseTheme(darkTheme = false) { PulseBottomBarPreviewContent() }
}

@Preview(name = "BottomBar – Dark", showBackground = true)
@Composable
private fun PulseBottomBarDarkPreview() {
    PulseTheme(darkTheme = true) { PulseBottomBarPreviewContent() }
}

@Composable
private fun PulseBottomBarPreviewContent() {
    var selected by androidx.compose.runtime.remember { androidx.compose.runtime.mutableIntStateOf(0) }
    val items =
        listOf(
            PulseBottomBarItem(label = "Home", icon = OutlinedHome, selectedIcon = FilledHome),
            PulseBottomBarItem(label = "Search", icon = OutlinedSearch, selectedIcon = FilledSearch),
            PulseBottomBarItem(label = "Profile", icon = OutlinedPerson, selectedIcon = FilledPerson),
        )
    PulseBottomBar(items = items, selectedIndex = selected, onItemSelected = { selected = it })
}

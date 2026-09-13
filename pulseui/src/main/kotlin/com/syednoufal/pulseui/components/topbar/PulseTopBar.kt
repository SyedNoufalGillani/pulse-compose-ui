package com.syednoufal.pulseui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syednoufal.pulseui.theme.PulseTheme

/**
 * Pulse UI's standard screen header. Wraps Material 3's [TopAppBar] with the navigation-back
 * and single-overflow-action pattern used by nearly every screen in the catalog app, so
 * individual screens configure title/back/actions instead of assembling `TopAppBar` slots by hand.
 *
 * @param title Screen title.
 * @param modifier Modifier applied to the app bar.
 * @param onNavigateBack When non-null, shows a back arrow that invokes this on tap.
 * @param actions Optional trailing icon-button row, e.g. `{ IconButton(...) { Icon(...) } }`.
 * @param centered Whether to render a [CenterAlignedTopAppBar] instead of the default start-aligned title.
 * @param scrollBehavior Optional [TopAppBarScrollBehavior] for collapse-on-scroll screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulseTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigateBack: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
    centered: Boolean = false,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    val navigationIcon: @Composable () -> Unit = {
        if (onNavigateBack != null) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigate back")
            }
        }
    }

    if (centered) {
        CenterAlignedTopAppBar(
            title = { Text(title) },
            modifier = modifier,
            navigationIcon = navigationIcon,
            actions = { actions() },
            colors = TopAppBarDefaults.topAppBarColors(),
            scrollBehavior = scrollBehavior,
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            modifier = modifier,
            navigationIcon = navigationIcon,
            actions = { actions() },
            colors = TopAppBarDefaults.topAppBarColors(),
            scrollBehavior = scrollBehavior,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "TopBar – Light", showBackground = true)
@Composable
private fun PulseTopBarLightPreview() {
    PulseTheme(darkTheme = false) {
        PulseTopBar(
            title = "Component Detail",
            onNavigateBack = {},
            actions = { IconButton(onClick = {}) { Icon(Icons.Filled.MoreVert, contentDescription = "More") } },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "TopBar – Dark", showBackground = true)
@Composable
private fun PulseTopBarDarkPreview() {
    PulseTheme(darkTheme = true) {
        PulseTopBar(title = "Pulse Catalog", centered = true)
    }
}

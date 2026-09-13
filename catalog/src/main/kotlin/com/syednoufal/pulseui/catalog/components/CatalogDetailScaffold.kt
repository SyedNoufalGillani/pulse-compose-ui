package com.syednoufal.pulseui.catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.components.snackbar.PulseSnackbarHost
import com.syednoufal.pulseui.components.snackbar.PulseSnackbarState
import com.syednoufal.pulseui.components.topbar.PulseTopBar

/**
 * Shared chrome for every component detail screen: a [PulseTopBar] with back navigation, a
 * scrollable content column with consistent padding, and an optional section title convention
 * via [CatalogSection]. Keeps individual screens focused on demonstrating their component
 * instead of re-declaring Scaffold/TopBar boilerplate.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogDetailScaffold(
    title: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarState: PulseSnackbarState? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { PulseTopBar(title = title, onNavigateBack = onNavigateBack) },
        snackbarHost = { snackbarState?.let { PulseSnackbarHost(it) } },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp),
            content = content,
        )
    }
}

/** A labeled grouping within a [CatalogDetailScaffold] body, separating distinct demo states. */
@Composable
fun CatalogSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp), content = content)
    }
}

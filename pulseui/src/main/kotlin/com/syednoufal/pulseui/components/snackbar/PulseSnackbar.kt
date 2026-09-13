package com.syednoufal.pulseui.components.snackbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syednoufal.pulseui.theme.PulseTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/** Semantic tone for a [PulseSnackbarHost] message, controlling its icon and color treatment. */
enum class PulseSnackbarTone {
    Neutral,
    Success,
    Error,
}

/**
 * A single queued snackbar message. Built by [PulseSnackbarState.show] rather than constructed
 * directly, so tone/icon/message always travel together through the host's message queue.
 */
@Immutable
private data class PulseSnackbarMessage(
    val message: String,
    val actionLabel: String?,
    val tone: PulseSnackbarTone,
    val icon: ImageVector?,
)

/**
 * Thin, coroutine-friendly wrapper over [SnackbarHostState] that adds Pulse UI's tone metadata
 * (see [PulseSnackbarTone]) to each message and exposes a simple [show] entry point instead of
 * requiring call sites to launch their own coroutine against `SnackbarHostState.showSnackbar`.
 *
 * Obtain one via [rememberPulseSnackbarState] and render it with [PulseSnackbarHost].
 */
@Immutable
class PulseSnackbarState internal constructor(
    internal val hostState: SnackbarHostState,
    private val scope: CoroutineScope,
) {
    /** Currently displayed tone, read by [PulseSnackbarHost] to color the rendered snackbar. */
    internal var currentTone: PulseSnackbarTone = PulseSnackbarTone.Neutral
        private set

    internal var currentIcon: ImageVector? = null
        private set

    /**
     * Enqueues [message] for display, dismissing any currently showing snackbar first (Material's
     * default `SnackbarHostState` behavior). Returns immediately; the message is shown
     * asynchronously on [scope].
     *
     * @param onActionPerformed Invoked if the user taps [actionLabel] before the snackbar times out.
     */
    fun show(
        message: String,
        actionLabel: String? = null,
        tone: PulseSnackbarTone = PulseSnackbarTone.Neutral,
        icon: ImageVector? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
        onActionPerformed: () -> Unit = {},
    ) {
        currentTone = tone
        currentIcon = icon
        scope.launch {
            val result =
                hostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = duration,
                )
            if (result == SnackbarResult.ActionPerformed) {
                onActionPerformed()
            }
        }
    }
}

/**
 * Creates and remembers a [PulseSnackbarState] bound to the current composition's coroutine
 * scope. Hoist the result to a screen-level `Scaffold` and pass it to both [PulseSnackbarHost]
 * (as the `hostState`) and any call site that needs to trigger a message.
 */
@Composable
fun rememberPulseSnackbarState(): PulseSnackbarState {
    val hostState = remember { SnackbarHostState() }
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    return remember(hostState, scope) { PulseSnackbarState(hostState, scope) }
}

/**
 * Renders queued messages from [state] with Pulse UI's tone-aware styling. Place this in a
 * `Scaffold`'s `snackbarHost` slot.
 */
@Composable
fun PulseSnackbarHost(
    state: PulseSnackbarState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(hostState = state.hostState, modifier = modifier) { data ->
        val tone = state.currentTone
        val icon = state.currentIcon
        val (container, content) =
            when (tone) {
                PulseSnackbarTone.Neutral ->
                    MaterialTheme.colorScheme.inverseSurface to MaterialTheme.colorScheme.inverseOnSurface
                PulseSnackbarTone.Success ->
                    MaterialTheme.colorScheme.tertiaryContainer to MaterialTheme.colorScheme.onTertiaryContainer
                PulseSnackbarTone.Error ->
                    MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
            }
        Snackbar(
            containerColor = container,
            contentColor = content,
            actionColor = content,
            action =
                data.visuals.actionLabel?.let { label ->
                    {
                        androidx.compose.material3.TextButton(onClick = { data.performAction() }) {
                            Text(label, color = content)
                        }
                    }
                },
        ) {
            androidx.compose.foundation.layout.Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            ) {
                if (icon != null) {
                    Icon(icon, contentDescription = null, tint = content)
                    androidx.compose.foundation.layout.Spacer(Modifier.padding(end = 8.dp))
                }
                Text(data.visuals.message)
            }
        }
    }
}

@Preview(name = "Snackbar – Light", showBackground = true)
@Composable
private fun PulseSnackbarLightPreview() {
    PulseTheme(darkTheme = false) { PulseSnackbarPreviewContent() }
}

@Preview(name = "Snackbar – Dark", showBackground = true)
@Composable
private fun PulseSnackbarDarkPreview() {
    PulseTheme(darkTheme = true) { PulseSnackbarPreviewContent() }
}

@Composable
private fun PulseSnackbarPreviewContent() {
    val state = rememberPulseSnackbarState()
    androidx.compose.foundation.layout.Box(modifier = Modifier.padding(16.dp)) {
        PulseSnackbarHost(state)
    }
    androidx.compose.runtime.LaunchedEffect(Unit) {
        state.show("Changes saved", tone = PulseSnackbarTone.Success, actionLabel = "Undo")
    }
}

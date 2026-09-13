package com.syednoufal.pulseui.catalog.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.syednoufal.pulseui.catalog.components.CatalogDetailScaffold
import com.syednoufal.pulseui.catalog.components.CatalogSection
import com.syednoufal.pulseui.components.rating.PulseRatingBar

/** Demonstrates [PulseRatingBar] as tappable input and as a read-only fractional average display. */
@Composable
fun RatingBarScreen(onNavigateBack: () -> Unit) {
    var rating by remember { mutableIntStateOf(0) }

    CatalogDetailScaffold(title = "Rating Bar", onNavigateBack = onNavigateBack) {
        CatalogSection(title = "Tappable input") {
            PulseRatingBar(rating = rating.toFloat(), onRatingChange = { rating = it })
            Text(
                text = if (rating == 0) "Tap a star to rate" else "You rated this $rating / 5",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        CatalogSection(title = "Read-only average (with half stars)") {
            PulseRatingBar(rating = 4.5f, onRatingChange = {}, readOnly = true)
            PulseRatingBar(rating = 2.5f, onRatingChange = {}, readOnly = true)
            PulseRatingBar(rating = 3f, onRatingChange = {}, readOnly = true)
        }
    }
}

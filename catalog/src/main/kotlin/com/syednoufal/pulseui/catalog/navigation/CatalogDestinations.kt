package com.syednoufal.pulseui.catalog.navigation

/**
 * Every navigable destination in the catalog app, grouped by the design-system category it
 * belongs to. [HomeScreen] renders one section per [CatalogCategory] and one row per
 * [CatalogDestination] within it; [PulseCatalogNavHost] maps each [route] onto its detail screen.
 */
enum class CatalogCategory(val label: String) {
    Foundations("Foundations"),
    Actions("Actions"),
    Inputs("Inputs"),
    Display("Display"),
    Navigation("Navigation"),
    Feedback("Feedback"),
}

enum class CatalogDestination(
    val route: String,
    val title: String,
    val summary: String,
    val category: CatalogCategory,
) {
    Home("home", "Pulse Catalog", "", CatalogCategory.Foundations),

    Theming(
        "theming",
        "Theming",
        "Color roles, dynamic color, and the semantic extended palette",
        CatalogCategory.Foundations,
    ),
    Typography(
        "typography",
        "Typography",
        "The full Pulse type scale from display to label",
        CatalogCategory.Foundations,
    ),
    Spacing(
        "spacing",
        "Spacing & Elevation",
        "The 8pt grid and tonal elevation steps",
        CatalogCategory.Foundations,
    ),

    Button("button", "Button", "Primary, secondary, tertiary, destructive, and loading states", CatalogCategory.Actions),

    TextField("textfield", "Text Field", "Error states, icon slots, and a character counter", CatalogCategory.Inputs),
    Switch("switch", "Switch", "A labeled, row-clickable toggle", CatalogCategory.Inputs),
    Slider("slider", "Slider", "Continuous and stepped numeric input", CatalogCategory.Inputs),
    SegmentedControl("segmented", "Segmented Control", "Single-choice view switcher", CatalogCategory.Inputs),

    Card("card", "Card", "Filled, outlined, and elevated content containers", CatalogCategory.Display),
    Chip("chip", "Chip", "Filter, assist, and input chip variants", CatalogCategory.Display),
    Avatar("avatar", "Avatar", "Photo avatars with a deterministic initials fallback", CatalogCategory.Display),
    Badge("badge", "Badge", "Status pills and unread dot indicators", CatalogCategory.Display),
    RatingBar("rating", "Rating Bar", "Tappable and read-only star ratings", CatalogCategory.Display),
    SkeletonLoader("skeleton", "Skeleton Loader", "Animated shimmer loading placeholders", CatalogCategory.Display),
    EmptyState("empty", "Empty State", "No-results, no-data, and offline placeholders", CatalogCategory.Display),

    TopBar("topbar", "Top App Bar", "Screen headers with back navigation and actions", CatalogCategory.Navigation),
    BottomBar("bottombar", "Bottom Navigation", "Primary bottom navigation destinations", CatalogCategory.Navigation),

    Snackbar("snackbar", "Snackbar", "Transient tone-aware messages with an undo action", CatalogCategory.Feedback),
    Dialog("dialog", "Dialog", "Confirmation and alert dialogs", CatalogCategory.Feedback),
    BottomSheet("bottomsheet", "Bottom Sheet", "A modal sheet for supplementary content", CatalogCategory.Feedback),
    ;

    companion object {
        /** All destinations except [Home], in category order, for [HomeScreen] to render. */
        val catalogEntries: List<CatalogDestination> = entries.filter { it != Home }
    }
}

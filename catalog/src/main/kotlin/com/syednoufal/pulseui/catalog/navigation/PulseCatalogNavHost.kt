package com.syednoufal.pulseui.catalog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.syednoufal.pulseui.catalog.screens.AvatarScreen
import com.syednoufal.pulseui.catalog.screens.BadgeScreen
import com.syednoufal.pulseui.catalog.screens.BottomBarScreen
import com.syednoufal.pulseui.catalog.screens.BottomSheetScreen
import com.syednoufal.pulseui.catalog.screens.ButtonScreen
import com.syednoufal.pulseui.catalog.screens.CardScreen
import com.syednoufal.pulseui.catalog.screens.ChipScreen
import com.syednoufal.pulseui.catalog.screens.DialogScreen
import com.syednoufal.pulseui.catalog.screens.EmptyStateScreen
import com.syednoufal.pulseui.catalog.screens.HomeScreen
import com.syednoufal.pulseui.catalog.screens.RatingBarScreen
import com.syednoufal.pulseui.catalog.screens.SegmentedControlScreen
import com.syednoufal.pulseui.catalog.screens.SkeletonLoaderScreen
import com.syednoufal.pulseui.catalog.screens.SliderScreen
import com.syednoufal.pulseui.catalog.screens.SnackbarScreen
import com.syednoufal.pulseui.catalog.screens.SpacingScreen
import com.syednoufal.pulseui.catalog.screens.SwitchScreen
import com.syednoufal.pulseui.catalog.screens.TextFieldScreen
import com.syednoufal.pulseui.catalog.screens.ThemingScreen
import com.syednoufal.pulseui.catalog.screens.TopBarScreen
import com.syednoufal.pulseui.catalog.screens.TypographyScreen
import com.syednoufal.pulseui.catalog.theme.CatalogThemeViewModel

/**
 * Root navigation graph for the catalog app: one [CatalogDestination.Home] listing screen plus
 * one detail screen per component/foundation entry in [CatalogDestination.catalogEntries].
 *
 * @param themeViewModel Shared theme preferences, passed through to [HomeScreen] where the
 * light/dark/system and dynamic-color controls live.
 */
@Composable
fun PulseCatalogNavHost(
    themeViewModel: CatalogThemeViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = CatalogDestination.Home.route) {
        composable(CatalogDestination.Home.route) {
            HomeScreen(
                themeViewModel = themeViewModel,
                onDestinationSelected = { destination -> navController.navigate(destination.route) },
            )
        }

        composable(CatalogDestination.Theming.route) { ThemingScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Typography.route) { TypographyScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Spacing.route) { SpacingScreen(onNavigateBack = navController::popBackStack) }

        composable(CatalogDestination.Button.route) { ButtonScreen(onNavigateBack = navController::popBackStack) }

        composable(CatalogDestination.TextField.route) { TextFieldScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Switch.route) { SwitchScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Slider.route) { SliderScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.SegmentedControl.route) {
            SegmentedControlScreen(onNavigateBack = navController::popBackStack)
        }

        composable(CatalogDestination.Card.route) { CardScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Chip.route) { ChipScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Avatar.route) { AvatarScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Badge.route) { BadgeScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.RatingBar.route) { RatingBarScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.SkeletonLoader.route) {
            SkeletonLoaderScreen(onNavigateBack = navController::popBackStack)
        }
        composable(CatalogDestination.EmptyState.route) { EmptyStateScreen(onNavigateBack = navController::popBackStack) }

        composable(CatalogDestination.TopBar.route) { TopBarScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.BottomBar.route) { BottomBarScreen(onNavigateBack = navController::popBackStack) }

        composable(CatalogDestination.Snackbar.route) { SnackbarScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.Dialog.route) { DialogScreen(onNavigateBack = navController::popBackStack) }
        composable(CatalogDestination.BottomSheet.route) { BottomSheetScreen(onNavigateBack = navController::popBackStack) }
    }
}

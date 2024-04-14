package com.thechance.newspal.ui.features.Explore

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thechance.newspal.ui.navigation.Screen

private val ROUTE = Screen.ExploreScreen.route


fun NavGraphBuilder.exploreRoute(
    navController: NavController
) {
    composable(ROUTE) { ExploreScreen(navController) }
}
package com.thechance.newspal.ui.features.Search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thechance.newspal.ui.navigation.Screen

private val ROUTE = Screen.SearchScreen.route

fun NavController.navigateToSearchScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.searchRoute(
    navController: NavController
) {

    composable(ROUTE) {
        SearchScreen(NavController = navController)
    }
}
package com.thechance.newspal.ui.features.Home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thechance.newspal.ui.navigation.Screen

private val ROUTE = Screen.HomeScreen.route



fun NavGraphBuilder.homeRoute(
    navController: NavController
) {

    composable(ROUTE) {
        HomeScreen(NavController = navController)
    }
}
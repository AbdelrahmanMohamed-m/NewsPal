package com.thechance.newspal.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.thechance.newspal.ui.features.BookMark.bookMarkRoute
import com.thechance.newspal.ui.features.Explore.exploreRoute
import com.thechance.newspal.ui.features.Home.homeRoute


fun NavGraphBuilder.bookMarkScreenNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.BookMarkScreen.route,
        route = Graph.bookMark
    ) {
        homeRoute(navController = navController)
        exploreRoute(navController = navController)
        bookMarkRoute()
    }
}
package com.thechance.newspal.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.thechance.newspal.ui.features.ArticleDetailsScreen.articleDetailsRoute
import com.thechance.newspal.ui.features.Explore.exploreRoute
import com.thechance.newspal.ui.features.Home.homeRoute
import com.thechance.newspal.ui.features.Search.searchRoute

fun NavGraphBuilder.homeNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.HomeScreen.route,
        route = Graph.HOME
    ) {
        homeRoute(navController = navController)
        searchRoute(navController = navController)
        articleDetailsRoute(navController = navController)
        exploreRoute(navController = navController)

    }
}


package com.thechance.newspal.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.thechance.newspal.ui.features.ArticleDetailsScreen.articleDetailsRoute
import com.thechance.newspal.ui.features.Home.homeRoute
import com.thechance.newspal.ui.features.Search.searchRoute


fun NavGraphBuilder.searchNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.SearchScreen.route,
        route = Graph.SEARCH
    ) {
        searchRoute(navController = navController)
        homeRoute(navController = navController)
        articleDetailsRoute(navController = navController)
    }
}
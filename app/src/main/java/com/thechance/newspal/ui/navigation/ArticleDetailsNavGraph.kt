package com.thechance.newspal.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.thechance.newspal.ui.features.ArticleDetailsScreen.articleDetailsRoute
import com.thechance.newspal.ui.features.Explore.exploreRoute
import com.thechance.newspal.ui.features.Home.homeRoute


fun NavGraphBuilder.articleDetailsNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.ArticleDetailsScreen.route,
        route = Graph.ARTICLE_DETAILS
    ) {
        articleDetailsRoute(navController = navController)
        homeRoute(navController = navController)
        exploreRoute(navController = navController)
    }
}
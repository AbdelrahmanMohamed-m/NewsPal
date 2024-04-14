package com.thechance.newspal.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.thechance.newspal.ui.features.ArticleDetailsScreen.articleDetailsRoute
import com.thechance.newspal.ui.features.BookMark.bookMarkRoute
import com.thechance.newspal.ui.features.Explore.exploreRoute
import com.thechance.newspal.ui.features.Home.homeRoute


fun NavGraphBuilder.exploreScreenNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.ExploreScreen.route,
        route = Graph.EXPLORE
    ) {
        homeRoute(
            navController = navController
        )
        exploreRoute(
            navController = navController
        )
        bookMarkRoute()
        articleDetailsRoute(
            navController = navController
        )
    }
}
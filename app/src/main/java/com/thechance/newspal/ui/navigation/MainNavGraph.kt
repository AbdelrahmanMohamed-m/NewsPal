package com.thechance.newspal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Graph.HOME
    ) {
        exploreScreenNavGraph(navController = navController)
        bookMarkScreenNavGraph(navController = navController)
        homeNavGraph(navController = navController)
        articleDetailsNavGraph(navController = navController)
        searchNavGraph(navController = navController)

    }
}

object Graph {
    const val HOME = "home_graph"
    const val bookMark = "Bookmark_graph"
    const val PROFILE = "profile_graph"
    const val EXPLORE = "explore_graph"
    const val ARTICLE_DETAILS = "article_details_graph"
    const val SEARCH = "search_graph"
    const val SETTINGS = "settings_graph"
}
package com.thechance.newspal.ui.navigation

sealed class Screen(
    val route: String

) {
    object HomeScreen : Screen(
        route = "homeScreen"
    )

    object ExploreScreen : Screen(
        route = "exploreScreen"
    )

    object BookMarkScreen : Screen(
        route = "BookMarkedScreen"
    )

    object ProfileScreen : Screen(
        route = "profileScreen"
    )
    object SearchScreen : Screen(
        route = "SearchScreen"
    )
    object ArticleDetailsScreen : Screen(
        route = "ArticleDetailsScreen"
    )
}
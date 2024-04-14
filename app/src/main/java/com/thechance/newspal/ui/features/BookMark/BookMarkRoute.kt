package com.thechance.newspal.ui.features.BookMark

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thechance.newspal.ui.navigation.Screen

private val ROUTE = Screen.BookMarkScreen.route

fun NavController.navigateToBookMarkScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.bookMarkRoute() {
    composable(ROUTE) { BookMarkedScreen() }
}
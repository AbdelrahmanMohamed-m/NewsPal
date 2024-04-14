package com.thechance.newspal.ui.features.BookMark

import com.thechance.newspal.ui.base.BaseUiEffect

sealed interface BookMarkedUiEffect: BaseUiEffect {
 object NavigateToArticleDetails: BookMarkedUiEffect

}
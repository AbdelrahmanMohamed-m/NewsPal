package com.thechance.newspal.di

import com.thechance.data.usecase.DeleteArticleUseCase
import com.thechance.data.usecase.GetArticlesFromDBUseCase
import com.thechance.data.usecase.GetBookmarkedStatusUseCase
import com.thechance.data.usecase.GetNewsUseCase
import com.thechance.data.usecase.HeadLinesUseCase
import com.thechance.data.usecase.InsertArticleToDBUseCase
import com.thechance.data.usecase.SearchUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single { GetNewsUseCase(get()) }
    single { SearchUseCase(get()) }
    single { HeadLinesUseCase(get()) }
    single { InsertArticleToDBUseCase(get()) }
    single { GetArticlesFromDBUseCase(get()) }
    single { DeleteArticleUseCase(get()) }
    single { GetBookmarkedStatusUseCase(get())}
}
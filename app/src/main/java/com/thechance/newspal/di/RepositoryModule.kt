package com.thechance.newspal.di

import com.thechance.data.repo.NewsRepoImpl
import com.thechance.data.usecase.NewsRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepo> {
        NewsRepoImpl(get(), get())
    }
}
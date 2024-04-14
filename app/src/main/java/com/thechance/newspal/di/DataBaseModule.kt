package com.thechance.newspal.di

import androidx.room.Room
import com.thechance.data.local.NewsPalDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), NewsPalDataBase::class.java, "news_pal_db")
            .build()
    }

    single { get<NewsPalDataBase>().articleDao }
}
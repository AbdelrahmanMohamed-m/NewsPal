package com.thechance.newspal

import android.app.Application
import com.thechance.newspal.di.dataBaseModule
import com.thechance.newspal.di.networkModule
import com.thechance.newspal.di.repositoryModule
import com.thechance.newspal.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import viewModelModule

class NewsPalApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsPalApp)
            modules(networkModule, repositoryModule, useCaseModule, viewModelModule, dataBaseModule)
        }
    }
}
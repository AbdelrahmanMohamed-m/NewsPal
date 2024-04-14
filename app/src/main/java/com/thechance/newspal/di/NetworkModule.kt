package com.thechance.newspal.di

import com.thechance.data.remote.source.NewsService
import com.thechance.newspal.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/v2/"

val networkModule = module {

    single {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            val request = original.newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(NewsService::class.java)
    }
}
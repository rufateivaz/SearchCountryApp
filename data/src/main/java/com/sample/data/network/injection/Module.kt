package com.sample.data.network.injection

import com.sample.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(get())
            .client(basicOkHttpClient())
            .build()
    }
}

fun httpInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun basicOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(httpInterceptor())
    .build()
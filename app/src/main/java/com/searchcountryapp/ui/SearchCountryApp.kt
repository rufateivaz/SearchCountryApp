package com.searchcountryapp.ui

import android.app.Application
import com.searchcountryapp.data.injection.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SearchCountryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SearchCountryApp)
            modules(dataModule)
        }

    }
}
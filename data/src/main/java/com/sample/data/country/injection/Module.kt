package com.sample.data.country.injection

import com.sample.data.country.CountryRepositoryImpl
import com.sample.data.country.local.CountryLocalDataSource
import com.sample.data.country.remote.CountryApi
import com.sample.data.country.remote.CountryRemoteDataSource
import com.sample.domain.CountryRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val countryDataModule = module {

    fun provideCountryApi(retrofit: Retrofit): CountryApi =
        retrofit.create(CountryApi::class.java)

    single<CountryRepository> {
        CountryRepositoryImpl(local = get(), remote = get())
    }

    single { CountryLocalDataSource(dao = get()) }
    single { CountryRemoteDataSource(api = get()) }
    single { provideCountryApi(retrofit = get()) }
}
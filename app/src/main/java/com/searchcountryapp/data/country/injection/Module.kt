package com.searchcountryapp.data.country.injection

import com.searchcountryapp.data.country.CountryRepositoryImpl
import com.searchcountryapp.data.country.local.CountryLocalDataSource
import com.searchcountryapp.data.country.remote.CountryApi
import com.searchcountryapp.data.country.remote.CountryRemoteDataSource
import com.searchcountryapp.domain.CountryRepository
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
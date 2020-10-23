package com.everton.trinitychallengeapp.di.modules

import com.everton.trinitychallengeapp.BuildConfig
import com.everton.trinitychallengeapp.data.local.TrinityDao
import com.everton.trinitychallengeapp.data.remote.NasaService
import com.everton.trinitychallengeapp.data.repository.TrinityRepository
import com.everton.trinitychallengeapp.data.repository.TrinityRepositoryImplementation
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val NetworkModule = module {
    single { createService(get()) }
    single { createOkHttpClient() }
    single { createRetrofit(get(), "https://api.nasa.gov/mars-photos/") }

}

fun createTrinityRepository(trinityDao: TrinityDao, nasaService: NasaService): TrinityRepository {
    return TrinityRepositoryImplementation(trinityDao, nasaService)
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun createService(retrofit: Retrofit): NasaService {
    return retrofit.create(NasaService::class.java)
}

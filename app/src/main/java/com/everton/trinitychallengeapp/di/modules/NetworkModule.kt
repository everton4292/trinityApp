package com.everton.trinitychallengeapp.di.modules

import com.everton.trinitychallengeapp.data.local.TrinityDao
import com.everton.trinitychallengeapp.data.repository.TrinityRepository
import com.everton.trinitychallengeapp.data.repository.TrinityRepositoryImplementation
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val NetworkModule = module {
    single { createOkHttpClient() }

}

fun createTrinityRepository(trinityDao: TrinityDao): TrinityRepository {
    return TrinityRepositoryImplementation(trinityDao)
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}

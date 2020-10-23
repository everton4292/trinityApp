package com.everton.trinitychallengeapp

import android.app.Application
import com.everton.trinitychallengeapp.di.modules.AppModule
import com.everton.trinitychallengeapp.di.modules.NetworkModule
import com.everton.trinitychallengeapp.di.modules.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TrinityApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@TrinityApplication)
            modules(listOf(AppModule, NetworkModule, dbModule))
        }
    }
}
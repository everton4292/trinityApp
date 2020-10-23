package com.everton.trinitychallengeapp.di.modules

import androidx.room.Room
import com.everton.trinitychallengeapp.data.local.TrinityDatabase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            get(),
            TrinityDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<TrinityDatabase>().trinityDao() }
}
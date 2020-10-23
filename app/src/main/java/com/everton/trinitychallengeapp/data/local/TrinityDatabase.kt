package com.everton.trinitychallengeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.everton.trinitychallengeapp.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class TrinityDatabase: RoomDatabase() {
    abstract fun trinityDao(): TrinityDao
}
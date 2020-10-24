package com.everton.trinitychallengeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.everton.trinitychallengeapp.data.model.Data
import com.everton.trinitychallengeapp.util.Converters

@Database(entities = [Data::class], version = 1, exportSchema = true)
@TypeConverters(
    Converters.CameraConverter::class,
    Converters.RoverConverters::class,
    Converters.DataConverter::class,
    Converters.PhotoConverter::class
)

abstract class TrinityDatabase: RoomDatabase() {
    abstract fun trinityDao(): TrinityDao
}
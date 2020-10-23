package com.everton.trinitychallengeapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = "email")
    val email: String = "",
    @ColumnInfo(name = "password")
    val password: String = ""
) : Parcelable
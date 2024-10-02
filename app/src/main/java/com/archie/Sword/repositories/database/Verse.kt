package com.archie.Sword.repositories.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyVersesTable")
data class Verse(


    val verse: String,
    val verseTag: String,

    val bookPosition: Byte,
    val date: Long,


    val themeName: String,
    val themeColor: String,

    val photoFilePath: String,

    //@ColumnInfo(name = "isPartOfFavorites", defaultValue = "0") included this when I was trying to add a new column to the database
    val isPartOfFavorites: Int,


    val note: String,

    @ColumnInfo(name = "memorisedCount")
    val memorisedCount: Int,


    val memorisedToday: Int,


    val memorisedTodayDate: String?,


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0



)

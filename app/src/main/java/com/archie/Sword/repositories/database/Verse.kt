package com.archie.Sword.repositories.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyVersesTable")
data class Verse(

    val verse: String,
    val bookName: String,
    val chapterAndVerseNumber: String,

    val bookPosition: Byte,
    val date: Long,


    val themeName: String,
    val themeColor: String,

    val photoFilePath: String,

    @ColumnInfo(name = "isPartOfFavorites", defaultValue = "0")
    val isPartOfFavorites: Int,

    @ColumnInfo(name = "note", defaultValue = " ")
    val note: String,

    @ColumnInfo(name = "Memorise Count", defaultValue = "0")
    val memorised: Int,

    @ColumnInfo(name = "Memorised Today", defaultValue = "0")
    val memorisedToday: Int,

    @ColumnInfo(name = "Memorised Date", defaultValue = "")
    val memorisedTodayDate: String?,


    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

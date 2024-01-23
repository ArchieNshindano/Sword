package com.archie.Sword.repositories.database

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

    val photoFilePath: String,

    val isPartOfFavourite: Boolean,



    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

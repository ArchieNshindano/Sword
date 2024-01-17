package com.archie.Sword.placeholders

import androidx.room.PrimaryKey
import com.archie.Sword.enums.Books

data class VerseHolder(

    val verse: String,
    val book: Books,
    val date: Long,
    val verseTag: VerseTag,
    val themeName: String,
    val themeColour: Int,
    val id: Int = 0
)

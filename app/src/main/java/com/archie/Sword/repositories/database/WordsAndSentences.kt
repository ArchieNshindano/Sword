package com.archie.Sword.repositories.database

import androidx.room.Entity


@Entity(tableName = "Words And Sentences")
data class WordsAndSentences(

    val todaysWord: String,
    val todaysSentence: String,
    val date: String

)

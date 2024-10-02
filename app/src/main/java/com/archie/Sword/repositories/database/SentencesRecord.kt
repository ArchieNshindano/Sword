package com.archie.Sword.repositories.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("SentencesRecord")
data class SentencesRecord (

    val wordToday: String,
    val sentenceToday: String,
    val date: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
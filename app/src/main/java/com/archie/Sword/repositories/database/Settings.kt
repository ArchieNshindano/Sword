package com.archie.Sword.repositories.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Settings")
data class Settings(

    val enableDarkMode: Boolean,
    val enableSystemTheme: Boolean,
    val enableVerseOfTheDay: Boolean,
    val enableDynamicTheme: Boolean,
    val enableDynamicSentence: Boolean,
    val enableDynamicWord: Boolean,
    val enableMediumContrast: Boolean,
    val enableHighContrast: Boolean,
    val enableDynamicColor: Boolean,

    val sortType: String,
    val selectedTheme: String,


    @PrimaryKey(autoGenerate = false)
    val id: Int = 1

)

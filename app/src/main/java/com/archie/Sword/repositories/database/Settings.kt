package com.archie.Sword.repositories.database

import androidx.room.Entity

@Entity(tableName = "Settings")
data class Settings(

    val enableDarkMode: Boolean,
    val sortType: String,
    val enableDynamicSentence: Boolean,
    val enableDynamicWord: Boolean,
    val selectedTheme: String,




)

package com.archie.Sword.repositories.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyThemesTable")
data class Theme(


    val themeName: String,
    val themeColor: String,
    val photoFilePath: String,
    val isPartOfFavorites: Int,
    val date: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0


)

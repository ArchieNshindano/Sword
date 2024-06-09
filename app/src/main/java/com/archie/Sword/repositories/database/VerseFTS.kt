package com.archie.Sword.repositories.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "SearchQueryTable")
@Fts4(contentEntity = Verse::class)
data class VerseFTS (

    @ColumnInfo(name = "rowid")
    @PrimaryKey
    val id: Int,

    val verse: String,
    val verseTag: String,

    val themeName: String,

    @ColumnInfo(name = "note")
    val note: String,


)
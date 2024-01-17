package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFunctions {

    @Upsert
    suspend fun addVerse(verse: Verse) = Unit


    @Query("SELECT * FROM MyVersesTable ORDER BY bookPosition ASC")
    fun getVerseByBook(): PagingSource<Int,Verse>

    @Query("SELECT * FROM MyVersesTable ORDER BY themeName ASC")
    fun getVersesByTheme(): PagingSource<Int,Verse>

    @Query("SELECT * FROM MyVersesTable ORDER BY date ASC")
    fun getVersesByDate(): PagingSource<Int,Verse>

    @Query("SELECT * FROM MyVersesTable")
    fun getVersesByDateFlow(): List<Verse>

    @Delete
     fun deletVerse(verse: Verse)




}
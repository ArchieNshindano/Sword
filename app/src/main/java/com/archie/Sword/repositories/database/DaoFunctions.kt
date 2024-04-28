package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoFunctions {

    @Upsert
    suspend fun addVerse(verse: Verse)


    @Query("SELECT * FROM MyVersesTable ORDER BY bookPosition ASC")
    fun getVerseByBook(): PagingSource<Int,Verse>

    @Query("SELECT * FROM MyVersesTable ORDER BY themeName ASC")
    fun getVersesByTheme(): PagingSource<Int,Verse>


    @Query("SELECT * FROM MyVersesTable ORDER BY date ASC")
    fun getVersesByDate(): PagingSource<Int,Verse>

    @Delete
    suspend fun deleteVerse(verse: Verse)


    @Update
    suspend fun updateVerse(verse: Verse)






    @Upsert
    fun addTheme(theme: Theme)


    @Query("SELECT * FROM MyThemesTable ORDER BY themeName ASC")
    fun getThemeByName(): Flow<Theme>

    @Query("SELECT * FROM MyThemesTable ORDER BY themeName ASC")
    fun getThemeByDate(): Flow<Theme>

    @Delete
    fun deleteTheme(theme: Theme)


    @Update
    suspend fun updateTheme(theme: Theme)











}
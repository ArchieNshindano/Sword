package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {



    suspend fun addVerse(verse: Verse)

    fun getVerseByBook(): PagingSource<Int, Verse>

    fun getVersesByTheme(): PagingSource<Int,Verse>

    fun getVersesByDate(): PagingSource<Int,Verse>

    fun deleteVerse(verse: Verse)





    suspend fun addTheme(theme: Theme)

    fun getThemeByName(): Flow<Theme>
    fun getThemeByDate(): Flow<Theme>

    fun deleteTheme(theme: Theme)

}
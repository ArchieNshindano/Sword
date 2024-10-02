package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface VersesDataBaseRepository {



    suspend fun addVerse(verse: Verse)

    fun getVerseByBook(): PagingSource<Int, Verse>

    fun getVersesByTheme(): PagingSource<Int,Verse>

    fun getVersesByDate(): PagingSource<Int,Verse>
    fun searchDatabaseUsingVerseTag(searchQuery: String):  Flow<List<Verse>>
    fun searchDatabaseUsingVerse(searchQuery: String):  Flow<List<Verse>>
    fun searchDatabaseUsingThemeName(searchQuery: String): Flow<List<Verse>>
    fun searchDatabaseUsingNotes(searchQuery: String): Flow<List<Verse>>


    suspend fun deleteVerse(verse: Verse)
    suspend fun updateVerse(theme: Verse)







//    suspend fun addTheme(theme: Theme)
//
//    fun getThemeByName(): Flow<Theme>
//    fun getThemeByDate(): Flow<Theme>
//
//    fun deleteTheme(theme: Theme)
//    suspend fun updateTheme(theme: Theme)

}
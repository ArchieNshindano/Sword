package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoFunctionsForVerses {


    // VERSE DATABASE DAO FUNCTIONS

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







    // SEARCH DATABASE DAO FUNCTIONS
    @Query("""
    SELECT * FROM MyVersesTable
    JOIN SearchQueryTable ON MyVersesTable.id == SearchQueryTable.rowid
    WHERE SearchQueryTable.verseTag MATCH :searchQuery 
    """)
    fun searchDatabaseUsingVerseTag(searchQuery: String): Flow< List<Verse> >

    @Query("""
    SELECT * FROM MyVersesTable
    JOIN SearchQueryTable ON MyVersesTable.id == SearchQueryTable.rowid
    WHERE SearchQueryTable.verse MATCH :searchQuery 
    """)
    fun searchDatabaseUsingVerse(searchQuery: String): Flow < List<Verse> >


    @Query("""
    SELECT * FROM MyVersesTable
    JOIN SearchQueryTable ON MyVersesTable.id == SearchQueryTable.rowid
    WHERE SearchQueryTable.themeName MATCH :searchQuery 
    """)
    fun searchDatabaseUsingThemeName(searchQuery: String): Flow < List<Verse> >

    @Query("""
    SELECT * FROM MyVersesTable
    JOIN SearchQueryTable ON MyVersesTable.id == SearchQueryTable.rowid
    WHERE SearchQueryTable.note MATCH :searchQuery 
    """)
    fun searchDatabaseUsingNotes(searchQuery: String): Flow < List<Verse> >



}
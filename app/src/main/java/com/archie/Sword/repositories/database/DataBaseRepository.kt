package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {


    suspend fun addVerse(verse: Verse)

    fun getVerseByBook(): PagingSource<Int, Verse>

    fun getVersesByTheme(): PagingSource<Int,Verse>

    fun getVersesByDate(): PagingSource<Int,Verse>

    fun deletVerse(verse: Verse)

    fun getVersesByDateFlow(): List<Verse>

}
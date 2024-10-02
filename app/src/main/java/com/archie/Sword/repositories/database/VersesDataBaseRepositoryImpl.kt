package com.archie.Sword.repositories.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VersesDataBaseRepositoryImpl @Inject constructor(val daos: DaoFunctionsForVerses): VersesDataBaseRepository{

    override suspend fun addVerse(verse: Verse)  = daos.addVerse(verse)

    override fun getVerseByBook() = daos.getVerseByBook()

    override fun getVersesByTheme() = daos.getVersesByTheme()

    override fun getVersesByDate() = daos.getVersesByDate()

    override fun searchDatabaseUsingVerseTag(searchQuery: String):Flow < List<Verse> > = daos.searchDatabaseUsingVerseTag(searchQuery)

    override fun searchDatabaseUsingVerse(searchQuery: String): Flow < List<Verse> > = daos.searchDatabaseUsingVerse(searchQuery)
    override fun searchDatabaseUsingThemeName(searchQuery: String): Flow < List<Verse> > = daos.searchDatabaseUsingThemeName(searchQuery)
    override fun searchDatabaseUsingNotes(searchQuery: String): Flow < List<Verse> > = daos.searchDatabaseUsingNotes(searchQuery)



    override suspend fun deleteVerse(verse: Verse) = daos.deleteVerse(verse)
    override suspend fun updateVerse(verse: Verse) = daos.updateVerse(verse)


}
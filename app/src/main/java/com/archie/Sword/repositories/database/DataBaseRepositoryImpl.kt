package com.archie.Sword.repositories.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor( val daos: DaoFunctions): DataBaseRepository{

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

    override suspend fun addTheme(theme: Theme) = daos.addTheme(theme)

    override fun getThemeByName(): Flow<Theme> = daos.getThemeByName()

    override fun getThemeByDate(): Flow<Theme> = daos.getThemeByDate()
    override fun deleteTheme(theme: Theme) = daos.deleteTheme(theme)
    override suspend fun updateTheme(theme: Theme) = daos.updateTheme(theme)

}
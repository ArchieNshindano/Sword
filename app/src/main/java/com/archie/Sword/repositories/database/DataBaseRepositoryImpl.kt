package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor( val daos: DaoFunctions): DataBaseRepository{

    override suspend fun addVerse(verse: Verse)  = daos.addVerse(verse)

    override fun getVerseByBook() = daos.getVerseByBook()

    override fun getVersesByTheme() = daos.getVersesByTheme()

    override fun getVersesByDate() = daos.getVersesByDate()

    override fun deleteVerse(verse: Verse) = daos.deleteVerse(verse)









    override suspend fun addTheme(theme: Theme) = daos.addTheme(theme)

    override fun getThemeByName(): Flow<Theme> = daos.getThemeByName()

    override fun getThemeByDate(): Flow<Theme> = daos.getThemeByDate()
    override fun deleteTheme(theme: Theme) = daos.deleteTheme(theme)

}
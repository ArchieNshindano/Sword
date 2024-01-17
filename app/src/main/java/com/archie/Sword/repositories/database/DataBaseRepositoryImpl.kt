package com.archie.Sword.repositories.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class DataBaseRepositoryImpl @Inject constructor( val daos: DaoFunctions): DataBaseRepository{

    override suspend fun addVerse(verse: Verse)  = daos.addVerse(verse)

    override fun getVerseByBook() = daos.getVerseByBook()


    override fun getVersesByTheme() = daos.getVersesByTheme()

    override fun getVersesByDate() = daos.getVersesByDate()

    override fun deletVerse(verse: Verse) = daos.deletVerse(verse)

    override fun getVersesByDateFlow() = daos.getVersesByDateFlow()




}
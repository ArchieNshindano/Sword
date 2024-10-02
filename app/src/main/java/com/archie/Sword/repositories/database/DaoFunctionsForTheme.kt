package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoFunctionsForTheme {

    @Upsert
    fun addTheme(theme: Theme)


    @Query("SELECT * FROM MyThemesTable ORDER BY themeName ASC")
    fun getThemeByName(): Flow<Theme>

    @Query("SELECT * FROM MyThemesTable ORDER BY themeName ASC")
    fun getThemeByDate(): Flow<Theme>

    @Delete
    fun deleteTheme(theme: Theme)





}
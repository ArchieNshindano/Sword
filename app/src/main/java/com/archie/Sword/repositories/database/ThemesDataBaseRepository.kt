package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface ThemesDataBaseRepository {


    suspend fun addTheme(theme: Theme)

    fun getThemeByName(): Flow<Theme>
    fun getThemeByDate(): Flow<Theme>

    fun deleteTheme(theme: Theme)


}
package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface SettingsDataBaseRepository {

    suspend fun addSettings(settings: Settings)

    fun getSettings(): Flow<Settings>

}
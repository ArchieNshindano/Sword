package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoFunctionsForSettings {

    @Upsert
    fun addSettings(settings: Settings)

    @Query("SELECT * FROM Settings")
    fun getSettings(): Flow<Settings>


}
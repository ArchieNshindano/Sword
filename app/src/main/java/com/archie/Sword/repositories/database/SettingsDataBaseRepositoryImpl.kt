package com.archie.Sword.repositories.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsDataBaseRepositoryImpl @Inject constructor(val daos: DaoFunctionsForSettings): SettingsDataBaseRepository{


    override suspend fun addSettings(settings: Settings) = daos.addSettings(settings)

    override fun getSettings(): Flow<Settings> = daos.getSettings()





}
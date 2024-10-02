package com.archie.Sword.repositories.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemesDataBaseRepositoryImpl @Inject constructor(val daos: DaoFunctionsForTheme): ThemesDataBaseRepository{


    override suspend fun addTheme(theme: Theme) = daos.addTheme(theme)

    override fun getThemeByName(): Flow<Theme> = daos.getThemeByName()

    override fun getThemeByDate(): Flow<Theme> = daos.getThemeByDate()
    override fun deleteTheme(theme: Theme) = daos.deleteTheme(theme)



}
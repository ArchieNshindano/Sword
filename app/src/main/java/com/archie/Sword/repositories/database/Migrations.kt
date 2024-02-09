package com.archie.Sword.repositories.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase




val Migration_2_3 = object: Migration(2,3){

    override fun migrate(database: SupportSQLiteDatabase) {

        database.execSQL("CREATE TABLE IF NOT EXISTS MyThemesTable " +
                "(themeName TEXT," +
                "themeColor TEXT," +
                "photoFilePath TEXT," +
                "isPartOfFavorites INTEGER," +
                "date INTEGER , " +
                "id INTEGER NOT NULL PRIMARY KEY)"

        )
    } // OVERRIDES ENDS
} // MIGRATION ENDS




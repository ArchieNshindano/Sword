package com.archie.Sword.repositories.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Verse::class, Theme::class],
    version = 4,

    autoMigrations = [

        AutoMigration(from = 3, to = 4)

    ]

)
abstract class VersesDatabase: RoomDatabase() {

    abstract fun daoFunctions(): DaoFunctions



}
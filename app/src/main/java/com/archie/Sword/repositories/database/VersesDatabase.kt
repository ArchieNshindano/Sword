package com.archie.Sword.repositories.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Verse::class, Theme::class],
    version = 1,

//    autoMigrations = [
//
//        AutoMigration(from = 1, to = 2)
//
//    ]

)
abstract class VersesDatabase: RoomDatabase() {

    abstract fun daoFunctions(): DaoFunctions



}
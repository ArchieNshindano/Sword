package com.archie.Sword.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Verse::class], version = 1)
abstract class VersesDatabase: RoomDatabase() {

    abstract fun daoFunctions(): DaoFunctions

}
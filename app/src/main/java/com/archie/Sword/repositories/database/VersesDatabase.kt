package com.archie.Sword.repositories.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec


@Database(
    entities = [Verse::class, Theme::class, VerseFTS::class],
    version = 1,



//    autoMigrations = [
//
//        AutoMigration(
//            from = 4,
//            to = 5
//            ),
//
//         AutoMigration(
//             from = 5,
//             to = 6,
//             spec = VersesDatabase.MyAutoMigration::class
//         )
//
//    ]

)
abstract class VersesDatabase: RoomDatabase() {

    abstract fun daoFunctions(): DaoFunctions



//    @RenameColumn(tableName = "MyVersesTable", fromColumnName = "bookName", toColumnName = "verseTag")
//    @RenameColumn(tableName = "SearchQueryTable", fromColumnName = "bookName", toColumnName = "verseTag")
//    @DeleteColumn(tableName = "MyVersesTable", columnName = "chapterAndVerseNumber")
//    @DeleteColumn(tableName = "SearchQueryTable", columnName = "chapterAndVerseNumber")
//    class MyAutoMigration: AutoMigrationSpec



}
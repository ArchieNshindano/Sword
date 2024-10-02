package com.archie.Sword.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Verse::class, Theme::class, VerseFTS::class, Settings::class, SentencesRecord::class],
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
//             spec = LocalDatabase.MyAutoMigration::class
//         )
//
//    ]

)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun daoFunctionsForVerses(): DaoFunctionsForVerses
    abstract fun daoFunctionsForThemes(): DaoFunctionsForTheme

    abstract fun daoFunctionsForSettings(): DaoFunctionsForSettings

    abstract fun daoFunctionsForSentencesRecord(): DaoFunctionsForSentencesRecord


//    @RenameColumn(tableName = "MyVersesTable", fromColumnName = "bookName", toColumnName = "verseTag")
//    @RenameColumn(tableName = "SearchQueryTable", fromColumnName = "bookName", toColumnName = "verseTag")
//    @DeleteColumn(tableName = "MyVersesTable", columnName = "chapterAndVerseNumber")
//    @DeleteColumn(tableName = "SearchQueryTable", columnName = "chapterAndVerseNumber")
//    class MyAutoMigration: AutoMigrationSpec



}
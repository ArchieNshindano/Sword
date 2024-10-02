package com.archie.Sword.daggerHilt

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.archie.Sword.repositories.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun databaseObject(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = LocalDatabase::class.java,
        name = "My Verses Database"

    ).addCallback(callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)


        }



        override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.d("Database", "Database Opened")
            }
        }

    ).build()




    @Provides
    @Singleton
    fun provideVersesDaoFunctions(database: LocalDatabase) = database.daoFunctionsForVerses()


    @Provides
    @Singleton
    fun provideThemesDaoFunctions(database: LocalDatabase) = database.daoFunctionsForThemes()

    @Provides
    @Singleton
    fun provideSettingsDaoFunctions(database: LocalDatabase) = database.daoFunctionsForSettings()


    @Provides
    @Singleton
    fun provideSentencesRecordDaoFunctions(database: LocalDatabase) = database.daoFunctionsForSentencesRecord()







}



package com.archie.Sword.daggerHilt

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import com.archie.Sword.repositories.database.DaoFunctions
import com.archie.Sword.repositories.database.VersesDatabase
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
        klass = VersesDatabase::class.java,
        name = "My Verses Database"

    ).build()


    @Provides
    @Singleton
    fun provideDaos(database: VersesDatabase) = database.daoFunctions()


}



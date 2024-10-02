package com.archie.Sword.repositories.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface  DaoFunctionsForSentencesRecord {


    @Upsert
    suspend fun addSentence(sentence: SentencesRecord)


    @Query("SELECT * FROM SentencesRecord ORDER BY date ASC")
    fun getSentencesByDate(): List<SentencesRecord>
}
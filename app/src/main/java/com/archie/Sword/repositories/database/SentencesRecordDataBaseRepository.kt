package com.archie.Sword.repositories.database

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface SentencesRecordDataBaseRepository {


    suspend fun addSentence(sentence: SentencesRecord)

    fun getSentencesByDate(): List<SentencesRecord>

}
package com.archie.Sword.repositories.database

import javax.inject.Inject

class SentencesRecordDataBaseRepositoryImpl @Inject constructor(val daos: DaoFunctionsForSentencesRecord): SentencesRecordDataBaseRepository{

    override suspend fun addSentence(sentence: SentencesRecord) = daos.addSentence(sentence)

    override fun getSentencesByDate(): List<SentencesRecord>  = daos.getSentencesByDate()




}
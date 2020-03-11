package com.adsi.ambiental.repository

import com.adsi.ambiental.data.Data
import com.adsi.ambiental.models.Answers
import com.adsi.ambiental.models.Questions

class PlayRepository {

    fun loadQuestions(): ArrayList<Questions>? {
        return Data.getQuestions()
    }

    fun loadAnswers(): ArrayList<List<Answers>>?{
        return Data.getAnswers()
    }

    suspend fun uploadScore(){

    }

}
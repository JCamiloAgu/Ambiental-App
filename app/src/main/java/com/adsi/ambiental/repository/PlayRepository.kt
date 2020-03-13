package com.adsi.ambiental.repository

import com.adsi.ambiental.data.Data
import com.adsi.ambiental.models.Answers
import com.adsi.ambiental.models.Questions
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class PlayRepository {

    fun loadQuestions(): ArrayList<Questions> = Data.getQuestions()


    fun loadAnswers(): ArrayList<MutableList<Answers>> {
        return Data.getAnswers()
    }

    suspend fun uploadScore() {

    }

}
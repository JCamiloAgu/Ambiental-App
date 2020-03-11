package com.adsi.ambiental.data

import com.adsi.ambiental.models.Answers
import com.adsi.ambiental.models.Questions
import java.util.*
import kotlin.collections.ArrayList

object Data {
    fun getAnswers(): ArrayList<List<Answers>>? {

        val answer1_1 = Answers(id = 1, idQuestion = 1, description = "El 56% del plástico es de un solo uso", isCorrect = true, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer1_2 = Answers(id = 2, idQuestion = 1, description = "El 96% del plástico es de un solo uso", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer1_3 = Answers(id = 3, idQuestion = 1, description = "El 50% del plástico es de un solo uso", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer1_4 = Answers(id = 4, idQuestion = 1, description = "El 20% del plástico es de un solo uso", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())

        val answersQuestion1: List<Answers> = listOf(answer1_1, answer1_2, answer1_3, answer1_4)

        val answer2_1 = Answers(id = 5, idQuestion = 2, description = "Adidas. Empresa de calzado elaborados por cartón", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer2_2 = Answers(id = 6, idQuestion = 2, description = "Puma. Empresa de calzado elaborados por plástico", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer2_3 = Answers(id = 7, idQuestion = 2, description = "Ecoflow. Empresa de calzado elaborados por tubos de PVC, botas pantaneras, etc.", isCorrect = true, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer2_4 = Answers(id = 8, idQuestion = 2, description = "Reebook. Empresa de calzado elaborados por tubos de PVC", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())

        val answersQuestion2: List<Answers> = listOf(answer2_1, answer2_2, answer2_3, answer2_4)

        val answer3_1 = Answers(id = 9, idQuestion = 3, description = "Rotar, reunir, recoger", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer3_2 = Answers(id = 10, idQuestion = 3, description = "Recoger, reutilizar, robar", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer3_3 = Answers(id = 11, idQuestion = 3, description = "Reutilizar, reciclar, reducir", isCorrect = true, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())
        val answer3_4 = Answers(id = 12, idQuestion = 3, description = "Reciclar, rotar, reutilizar", isCorrect = false, isActive = true, createdAt = Date().toString(), updateAt = Date().toString())

        val answersQuestion3: List<Answers> = listOf(answer3_1, answer3_2, answer3_3, answer3_4)

        return arrayListOf(answersQuestion1, answersQuestion2, answersQuestion3)
    }

    fun getQuestions(): ArrayList<Questions>? {
        val question1 = Questions(1, "¿Cuál es el porcentaje de plástico de un solo uso en Colombia?", "", true, Date().toString(), Date().toString())
        val question2 = Questions(2, "¿Cuál empresa está haciendo calzado con productos reciclados?", "", true, Date().toString(), Date().toString())
        val question3 = Questions(3, "¿Qué son las 3R en el medio ambiente?", "", true, Date().toString(), Date().toString())
        return arrayListOf(question1, question2, question3)
    }
}
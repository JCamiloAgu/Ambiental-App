package com.adsi.ambiental.models

data class Answers(
    val id: Int?,
    val idQuestion: Int,
    val description: String,
    val isCorrect: Boolean,
    val isActive: Boolean,
    val createdAt: String,
    val updateAt: String
)
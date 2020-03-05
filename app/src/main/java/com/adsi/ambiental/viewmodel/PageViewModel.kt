package com.adsi.ambiental.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adsi.ambiental.commons.default
import com.adsi.ambiental.models.Answers
import com.adsi.ambiental.models.Questions
import com.adsi.ambiental.repository.PlayRepository

class PageViewModel : ViewModel() {

    // Repository
    lateinit var repository: PlayRepository

    private val _questions = MutableLiveData<ArrayList<Questions>>(loadQuestions())

    val questions = _questions

    //TextViews
    val textMain: MutableLiveData<String> = MutableLiveData("")
    val textDescription: MutableLiveData<String> = MutableLiveData("")

    //RG Visibility
    val isVisibleRadioGroup: MutableLiveData<Int> = MutableLiveData<Int>().default(View.GONE)


    //RB text
    val radioButtonText1 = MutableLiveData<String>("")
    val radioButtonText2 = MutableLiveData<String>("")
    val radioButtonText3 = MutableLiveData<String>("")
    val radioButtonText4 = MutableLiveData<String>("")

    //Btn text
    val btnMainText = MutableLiveData<String>("")

    //Load Data
//    private fun loadQuestions(): ArrayList<Answers>? = repository.loadQuestions()
    private fun loadQuestions(): ArrayList<Questions>? =
        arrayListOf(
            Questions(
                id = 1,
                image = "",
                description = "Hola desde descripcion",
                isActive = true,
                createdAt = "hoy",
                updateAt = "hoy"
            )
        )


}
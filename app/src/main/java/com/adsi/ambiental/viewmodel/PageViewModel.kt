package com.adsi.ambiental.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adsi.ambiental.commons.default

class PageViewModel : ViewModel() {

    val textMain: MutableLiveData<String> = MutableLiveData("")
    val textDescription: MutableLiveData<String> = MutableLiveData("")

    val isVisibleRadioGroup: MutableLiveData<Int> = MutableLiveData<Int>().default(View.GONE)

    val radioButtonText1 = MutableLiveData<String>("")
    val radioButtonText2 = MutableLiveData<String>("")
    val radioButtonText3 = MutableLiveData<String>("")
    val radioButtonText4 = MutableLiveData<String>("")

    val btnMainText = MutableLiveData<String>("")

    fun loadQuestions(){

    }


}
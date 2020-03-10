package com.adsi.ambiental.viewmodel

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adsi.ambiental.models.Questions
import com.adsi.ambiental.repository.PlayRepository

class PlayViewModel : ViewModel() {


    // Repository
    lateinit var repository: PlayRepository

    private val _questions = MutableLiveData<ArrayList<Questions>>(loadQuestions())

    val questions = _questions

    //TextViews
    val textMain = MutableLiveData("")
    val textDescription = MutableLiveData("")

    //RG Visibility
    val visibilityRadioGroup = MutableLiveData(View.GONE)

    //Progress Bar Properties
    val visibilityProgressBar = MutableLiveData(View.GONE)
    private val _progress = MutableLiveData(200)
    val progress = _progress

    var countDownTimer: CountDownTimer? = null

    //Img visibility
    val visibilityImg = MutableLiveData(View.GONE)


    //RB text
    val radioButtonText1 = MutableLiveData("")
    val radioButtonText2 = MutableLiveData("")
    val radioButtonText3 = MutableLiveData("")
    val radioButtonText4 = MutableLiveData("")

    //Btn text
    val btnMainText = MutableLiveData("")

    fun playProgressBar() {
        _progress.value = 200

        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(10000, 50) {
                override fun onFinish() {
                    Log.d("Finish", "Finish")
                }

                override fun onTick(millisUntilFinished: Long) {
                    _progress.value = _progress.value!! - 1
                }

            }.start()
        } else {
            countDownTimer.apply {
                this!!.cancel()
                this.start()
            }
        }
    }


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
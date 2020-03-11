package com.adsi.ambiental.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adsi.ambiental.R
import com.adsi.ambiental.models.Answers
import com.adsi.ambiental.models.Questions
import com.adsi.ambiental.repository.PlayRepository


class PlayViewModel : ViewModel() {

    // Repository
    lateinit var repository: PlayRepository

    private val questions  by lazy { loadQuestions()!!}
    private val answers: ArrayList<List<Answers>> by lazy { loadAnswers()!! }

    private lateinit var question: Questions
    private var answer: MutableList<Answers> = mutableListOf()

    //TextViews
    val textMain = MutableLiveData("")
    val textDescription = MutableLiveData("")

    //RadioGroup Visibility
    val visibilityRadioGroup = MutableLiveData(View.GONE)

    //Progress Bar Properties
    val visibilityProgressBar = MutableLiveData(View.GONE)
    private val _progress = MutableLiveData(200)
    val progress = _progress

    var countDownTimer: CountDownTimer? = null

    //Img properties
    val visibilityImg = MutableLiveData(View.GONE)
    val imageDrawable = MutableLiveData<Drawable>(null)


    @BindingAdapter("android:src")
    fun setImageDrawable(view: ImageView, drawable: Drawable?) {
        view.setImageDrawable(drawable)
    }

    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }

    //RB text
    val radioButtonText1 = MutableLiveData("")
    val radioButtonText2 = MutableLiveData("")
    val radioButtonText3 = MutableLiveData("")
    val radioButtonText4 = MutableLiveData("")

    //Button action properties
    val btnActionText = MutableLiveData("")
    val btnActionIsEnable = MutableLiveData(true)

    //Load Data
    private fun loadQuestions(): ArrayList<Questions>? = repository.loadQuestions()
    private fun loadAnswers(): ArrayList<List<Answers>>? = repository.loadAnswers()

    var questionIndex = 0

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

    fun loadQuestion() {
        val question = questions[questionIndex]

        var iterator = 1
        answers.forEach {
            it.forEach { mAnswers ->
                if(mAnswers.idQuestion == question.id){
                    mAnswers.description.apply {
                        when (iterator){
                            1 -> radioButtonText1.value = this
                            2 -> radioButtonText2.value = this
                            3 -> radioButtonText3.value = this
                            4 -> radioButtonText4.value = this
                        }
                    }
                    answer.add(mAnswers)
                    iterator ++
                }

            }
        }
        textDescription.value = question.description
        textMain.value = "PREGUNTA ${question.id}"

        questionIndex ++
    }

    fun validateAnswer(i: Int) {
        when (i){
            1 -> {
                if(answer[i].isCorrect){

                }
            }
        }
    }




}
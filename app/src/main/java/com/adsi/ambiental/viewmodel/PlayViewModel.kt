package com.adsi.ambiental.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.adsi.ambiental.R
import com.adsi.ambiental.models.Answers
import com.adsi.ambiental.models.Questions
import com.adsi.ambiental.repository.PlayRepository


class PlayViewModel(application: Application) : AndroidViewModel(application) {

    val app = application

    // Repository
    lateinit var repository: PlayRepository

    private lateinit var questions: ArrayList<Questions>
    private lateinit var answers: ArrayList<MutableList<Answers>>

    private var answer: MutableList<Answers> = mutableListOf()

    //TextViews
    val textMain = MutableLiveData("")
    val textDescription = MutableLiveData("")

    //RadioGroup Visibility
    val visibilityRadioGroup = MutableLiveData(View.GONE)

    //Progress Bar Properties
    val visibilityProgressBar = MutableLiveData(View.GONE)
    private val _progress = MutableLiveData(200)
    var progress = _progress

    var countDownTimer: CountDownTimer? = null
    val isCountDownTimerFinished = MutableLiveData(false)

    //Img properties
    val visibilityImg = MutableLiveData(View.GONE)
    val imageDrawable = MutableLiveData<Drawable>(null)

    //RB text
    val radioButtonText1 = MutableLiveData("")
    val radioButtonText2 = MutableLiveData("")
    val radioButtonText3 = MutableLiveData("")
    val radioButtonText4 = MutableLiveData("")

    //Button action properties
    val btnActionText = MutableLiveData("")
    val btnActionIsEnable = MutableLiveData(true)

    //Load Data
    fun loadData() {
        loadQuestions()
        loadAnswers()
    }

    private fun loadQuestions() {
        val q = repository.loadQuestions()
        q.shuffle()
        questions = q
    }

    private fun loadAnswers() {
       answers = repository.loadAnswers()
    }

    //Game Status
    val isGameFinished = MutableLiveData(false)
    var questionIndex = 0

    fun playProgressBar() {
        _progress.value = 200

        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(10000, 50) {
                override fun onFinish() {
                    isCountDownTimerFinished.value = true
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
        if (questionIndex < questions.size) {
            val question = questions[questionIndex]
            answer.clear()

            var iterator = 1
            answers.forEach {
                it.shuffle()
                it.forEach { mAnswers ->
                    if (mAnswers.idQuestion == question.id) {
                        mAnswers.description.apply {
                            when (iterator) {
                                1 -> radioButtonText1.value = this
                                2 -> radioButtonText2.value = this
                                3 -> radioButtonText3.value = this
                                4 -> radioButtonText4.value = this
                            }
                        }
                        answer.add(mAnswers)
                        iterator++
                    }

                }
            }
            textDescription.value = question.description
            textMain.value = "PREGUNTA ${questionIndex + 1}"

            questionIndex++
        } else {
            isGameFinished.value = true
        }
    }

    fun validateAnswer(index: Int) {
        if (index >= 0) {
            if (answer[index].isCorrect)
                imageDrawable.value = app.getDrawable(R.drawable.ic_check)
            else
                imageDrawable.value = app.getDrawable(R.drawable.ic_error)
        } else
            imageDrawable.value = app.getDrawable(R.drawable.ic_error)
    }

    fun resetGame() {
        btnActionIsEnable.value = true
        questionIndex = 0
        visibilityRadioGroup.value = View.GONE
        visibilityProgressBar.value = View.GONE
        isGameFinished.value = false
    }


}
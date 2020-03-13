package com.adsi.ambiental.ui.main.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adsi.ambiental.R
import com.adsi.ambiental.databinding.FragmentMainBinding
import com.adsi.ambiental.repository.PlayRepository
import com.adsi.ambiental.viewmodel.PlayViewModel

class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var radioGroupObservable: MutableLiveData<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playViewModel = ViewModelProvider(this).get(PlayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpBinding(inflater, container)
        setUpDependencies()

        radioGroupObservable = MutableLiveData(binding.rg.checkedRadioButtonId)

        setUpDefaultTextIntoLayout()
        setUpObservers()
        setUpOnClickListeners()

        return binding.root
    }

    private fun setUpObservers() {
        playViewModel.isCountDownTimerFinished.observe(viewLifecycleOwner, Observer {
            if (it)
                validateAnswers()
        })

        playViewModel.isGameFinished.observe(viewLifecycleOwner, Observer {
            if (it) {
                playViewModel.countDownTimer?.cancel()
                createAndShowEndGameDialog()
            }
        })

    }

    private fun animateImage(isImageVisible: Boolean) {
        val mAlpha = if (isImageVisible) 1f else 0f
        val mAnimateAlpha = if (isImageVisible) 0f else 1f

        binding.imgViewAnswerStatus.apply {
            alpha = mAlpha

            if (!isImageVisible)
                playViewModel.visibilityImg.value = View.VISIBLE

            animate()
                .alpha(mAnimateAlpha)
                .setDuration(1000.toLong())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (!isImageVisible)
                            animateImage(true)
                        else {
                            binding.rg.clearCheck()
                            playViewModel.visibilityImg.value = View.GONE
                            playViewModel.playProgressBar()
                            playViewModel.loadQuestion()
                            binding.rg.isEnabled = true
                        }
                    }
                })
        }
    }

    private fun setUpBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewmodel = playViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpOnClickListeners() {
        binding.btnAction.setOnClickListener {
            playViewModel.apply {
                visibilityRadioGroup.value = View.VISIBLE
                visibilityProgressBar.value = View.VISIBLE

                if (playViewModel.btnActionText.value == getString(R.string.next)) {
                    countDownTimer!!.cancel()
                    btnActionIsEnable.value = false
                    validateAnswers()
                } else {
                    btnActionText.value = getString(R.string.next)
                    btnActionIsEnable.value = false
                    loadData()
                    loadQuestion()
                    playProgressBar()
                }
            }
        }

        //RadioGroup buttons listener
        binding.rg.setOnCheckedChangeListener { _, id: Int ->
            playViewModel.btnActionIsEnable.value = id != -1
        }
    }

    private fun validateAnswers() {
        binding.rg.isEnabled = false
        val rbChecked = binding.rg.findViewById<RadioButton>(binding.rg.checkedRadioButtonId)
        val index: Int = binding.rg.indexOfChild(rbChecked)

        playViewModel.validateAnswer(index)
        animateImage(false)
    }

    private fun setUpDefaultTextIntoLayout() {
        playViewModel.textMain.value = getString(R.string.txt_main)
        playViewModel.textDescription.value = getString(R.string.loremp)
        playViewModel.btnActionText.value = getString(R.string.btn_start)
    }

    private fun setUpDependencies() {
        val repository = PlayRepository()
        playViewModel.repository = repository
    }

    private fun createAndShowEndGameDialog() {
        AlertDialog.Builder(context!!).apply {
                setTitle("Fin del juego")
                setMessage("HAS TERMINADO EL JUEGO")
                setPositiveButton(android.R.string.ok) { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }
            }.show()
            .setOnDismissListener {
                setUpDefaultTextIntoLayout()
                playViewModel.resetGame()
            }
    }


}
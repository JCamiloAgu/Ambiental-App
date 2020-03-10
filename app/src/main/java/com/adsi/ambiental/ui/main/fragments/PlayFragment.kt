package com.adsi.ambiental.ui.main.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adsi.ambiental.R
import com.adsi.ambiental.databinding.FragmentMainBinding
import com.adsi.ambiental.repository.PlayRepository
import com.adsi.ambiental.viewmodel.PlayViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_main.view.*

class PlayFragment : Fragment() {

    private lateinit var playViewModel: PlayViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var root: View


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

        root = binding.root

        setUpTextIntoLayout()
        setUpOnClickListeners()

        return root
    }

    private fun animateImage(isImageVisible: Boolean) {
        val mAlpha = if (isImageVisible) 1f else 0f
        val mAnimateAlpha = if (isImageVisible) 0f else 1f

        root.imgViewAnswerStatus.apply {
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
                            playViewModel.visibilityImg.value = View.GONE
                            playViewModel.playProgressBar()
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
                btnMainText.value = getString(R.string.next)
                visibilityProgressBar.value = View.VISIBLE

                if ((it as MaterialButton).text.toString() == getString(R.string.next)) {
                    playViewModel.countDownTimer!!.cancel()
                    animateImage(false)
                } else {
                    loadOneQuestion()
                    playProgressBar()
                }
            }
        }
    }


    private fun loadOneQuestion() {
        val question = playViewModel.questions.value?.get(0)
        playViewModel.textDescription.value = question?.description
        playViewModel.textMain.value = "PREGUNTA ${question?.id}"

    }

    private fun setUpTextIntoLayout() {
        playViewModel.textMain.value = getString(R.string.txt_main)
        playViewModel.textDescription.value = getString(R.string.loremp)
        playViewModel.btnMainText.value = getString(R.string.btn_start)
    }

    private fun setUpDependencies() {
        val repository = PlayRepository()
        playViewModel.repository = repository
    }

}
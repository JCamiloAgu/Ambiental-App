package com.adsi.ambiental.ui.main.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adsi.ambiental.R
import com.adsi.ambiental.databinding.FragmentMainBinding
import com.adsi.ambiental.viewmodel.PageViewModel
import com.adsi.ambiental.repository.PlayRepository
import kotlinx.android.synthetic.main.fragment_main.*

class PlayFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setUpBinding(inflater, container)
        setUpDependencies()

        val root = binding.root

        setUpTextIntoLayout()
        setUpOnClickListeners()

        //TODO -- Probar que valores me imprime
        Log.d("DATA", pageViewModel.questions.toString())

        //TODO -- Probar esta animación
        rg.visibility = View.VISIBLE
        val anim = ObjectAnimator.ofFloat(rg, "alpha", 0f, 1f)
        anim.duration = 1000
        anim.start()

        return root
    }

    private fun setUpBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewmodel = pageViewModel
        binding.lifecycleOwner = this
    }

    private fun setUpOnClickListeners() {
        binding.btnAction.setOnClickListener {
            pageViewModel.apply {
                isVisibleRadioGroup.value = View.VISIBLE
                btnMainText.value = getString(R.string.next)
            }
        }
    }

    private fun setUpTextIntoLayout() {
        pageViewModel.textMain.value = getString(R.string.txt_main)
        pageViewModel.textDescription.value = getString(R.string.loremp)
        pageViewModel.btnMainText.value = getString(R.string.btn_start)
    }

    private fun setUpDependencies(){
        val repository = PlayRepository()
        pageViewModel.repository = repository
    }

}
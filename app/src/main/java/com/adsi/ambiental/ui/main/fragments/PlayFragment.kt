package com.adsi.ambiental.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adsi.ambiental.R
import com.adsi.ambiental.databinding.FragmentMainBinding
import com.adsi.ambiental.repository.PlayRepository
import com.adsi.ambiental.viewmodel.PageViewModel

class PlayFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var root: View

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

        root = binding.root

        setUpTextIntoLayout()
        setUpOnClickListeners()

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
                playProgressBar()
                isVisibleProgressBar.value = View.VISIBLE
            }
        }
    }

    private fun setUpTextIntoLayout() {
        pageViewModel.textMain.value = getString(R.string.txt_main)
        pageViewModel.textDescription.value = getString(R.string.loremp)
        pageViewModel.btnMainText.value = getString(R.string.btn_start)
    }

    private fun setUpDependencies() {
        val repository = PlayRepository()
        pageViewModel.repository = repository
    }

}
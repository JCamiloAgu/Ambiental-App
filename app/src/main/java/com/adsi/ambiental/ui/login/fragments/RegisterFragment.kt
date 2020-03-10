package com.adsi.ambiental.ui.login.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adsi.ambiental.R
import com.adsi.ambiental.RegisterViewModel
import kotlinx.android.synthetic.main.register_fragment.view.*


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() =
            RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.register_fragment, container, false)
        root.imgBtnBack.setOnClickListener {
            activity?.onBackPressed()
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }



}

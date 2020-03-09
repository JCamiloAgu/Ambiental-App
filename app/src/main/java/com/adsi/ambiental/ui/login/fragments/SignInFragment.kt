package com.adsi.ambiental.ui.login.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.adsi.ambiental.R
import com.adsi.ambiental.commons.toast
import com.adsi.ambiental.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.sign_in_fragment.view.*

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.sign_in_fragment, container, false)
        root.btnGoRegister.setOnClickListener {
              findNavController().navigate(R.id.action_signInFragment_to_registerFragment2)
        }

        root.btnLogin.setOnClickListener{
            findNavController().navigate(R.id.action_signInFragment_to_mainActivity)
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

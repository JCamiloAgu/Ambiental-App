package com.adsi.ambiental.ui.main.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.adsi.ambiental.R
import com.adsi.ambiental.adapters.RecyclerAdapterRanking
import com.adsi.ambiental.viewmodel.RankViewModel
import kotlinx.android.synthetic.main.rank_fragment.*
import kotlinx.android.synthetic.main.rank_fragment.view.*

class RankFragment : Fragment() {

    companion object {
        fun newInstance() = RankFragment()
    }

    private lateinit var viewModel: RankViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.rank_fragment, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RankViewModel::class.java)
        // TODO: Use the ViewModel
        val adapter = RecyclerAdapterRanking(context!!)
        rcViewRanking.adapter = adapter
        rcViewRanking.layoutManager = LinearLayoutManager(context!!)
        rcViewRanking.setHasFixedSize(true)
    }

}

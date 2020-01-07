package com.study.wanandroid.nagivation.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.wanandroid.R

import com.study.wanandroid.nagivation.viewmodel.NagivationViewModel

class NagivationFragment : Fragment() {

    companion object {
        fun newInstance() = NagivationFragment()
    }

    private lateinit var viewModel: NagivationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nagivation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NagivationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

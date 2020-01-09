package com.study.wanandroid.wechat.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.wanandroid.R

import com.study.wanandroid.wechat.viewmoodel.WeChatViewModel

class WeChatFragment : Fragment() {

    companion object {
        fun newInstance() = WeChatFragment()
    }

    private lateinit var viewModel: WeChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.we_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeChatViewModel::class.java)
    }

}

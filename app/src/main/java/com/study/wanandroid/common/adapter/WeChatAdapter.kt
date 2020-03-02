package com.study.wanandroid.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * @author dengdai
 * @description
 */
@Suppress("DEPRECATION")
class WeChatAdapter(
    manager: FragmentManager,
    private val titles: List<String>,
    private val fragemtns: List<Fragment>
) : FragmentStatePagerAdapter(manager) {

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    override fun getItem(position: Int): Fragment = fragemtns[position]

    override fun getCount(): Int = fragemtns.size


}
package com.study.wanandroid.common

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @author dengdai
 * @description
 */
class MyFlexboxLayoutManager(context: Context) : FlexboxLayoutManager(context) {

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return when (lp) {
            is RecyclerView.LayoutParams -> {
                LayoutParams(lp)
            }
            is ViewGroup.MarginLayoutParams -> {
                LayoutParams(lp)
            }
            else -> {
                super.generateLayoutParams(lp)
            }
        }
    }
}
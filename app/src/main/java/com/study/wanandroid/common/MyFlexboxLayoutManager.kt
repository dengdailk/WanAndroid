package com.study.wanandroid.common

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @author dengdai
 * @description
 */
class MyFlexboxLayoutManager : FlexboxLayoutManager{

    constructor(context: Context): super(context)

    constructor(context: Context, flexDirection: Int): super(context, flexDirection)

    constructor(context: Context, flexDirection: Int,flexWrap : Int): super(context,flexDirection,flexWrap)

    override fun generateLayoutParams(lp: ViewGroup.LayoutParams?): RecyclerView.LayoutParams {
        return if(lp is RecyclerView.LayoutParams){
            FlexboxLayoutManager.LayoutParams(lp)
        }else if(lp is ViewGroup.MarginLayoutParams){
            FlexboxLayoutManager.LayoutParams(lp)
        }else{
            super.generateLayoutParams(lp)
        }
    }
}
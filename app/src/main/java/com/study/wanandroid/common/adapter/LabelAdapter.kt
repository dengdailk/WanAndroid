package com.study.wanandroid.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.wanandroid.R
import com.study.wanandroid.nagivation.data.LableRsp

/**
 * @author dengdai
 * @description
 */
class LabelAdapter (layoutId : Int,lables : List<LableRsp>?) : BaseQuickAdapter<LableRsp,BaseViewHolder>(layoutId,lables){

    override fun convert(helper: BaseViewHolder, item: LableRsp?) {
        with(helper){
            setText(R.id.mLable,item?.title)
            addOnClickListener(R.id.mLable)
        }
    }
}
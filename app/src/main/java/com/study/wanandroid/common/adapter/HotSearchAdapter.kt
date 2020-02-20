package com.study.wanandroid.common.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.wanandroid.R
import com.study.wanandroid.search.data.HotSearchRsp

/**
 * @author dengdai
 * @description
 */
class HotSearchAdapter(layoutId : Int,lables : List<HotSearchRsp>?) : BaseQuickAdapter<HotSearchRsp, BaseViewHolder>(layoutId,lables){

    override fun convert(helper: BaseViewHolder, item: HotSearchRsp?) {
        with(helper){
            setText(R.id.mLable,item?.name)
            addOnClickListener(R.id.mLable)
        }
    }
}
package com.study.wanandroid.common.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.wanandroid.R
import com.study.wanandroid.search.data.db.Record

/**
 * @author dengdai
 * @description
 */
class HistorySearchAdapter(@LayoutRes layoutId:  Int,history : List<Record>?) : BaseQuickAdapter<Record,BaseViewHolder>(layoutId,history){

    override fun convert(helper: BaseViewHolder, item: Record?) {
        with(helper){
            setText(R.id.mLable,item?.name)
            addOnClickListener(R.id.mLable)
        }
    }
}
package com.study.wanandroid.common.adapter

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.wanandroid.R
import com.study.wanandroid.nagivation.data.NagivationCategoryRsp

/**
 * @author dengdai
 * @description
 */
class CategoryAdapter (layoutId : Int,categoryList : List<NagivationCategoryRsp>?) : BaseQuickAdapter<NagivationCategoryRsp,BaseViewHolder>(layoutId,categoryList){

    var selectPosition = 0

    override fun convert(helper: BaseViewHolder, item: NagivationCategoryRsp?) {
        with(helper){
            setText(R.id.mCategory,item?.name)
            addOnClickListener(R.id.mCategory)

            if(adapterPosition == selectPosition){
                setBackgroundColor(R.id.mCategory, ContextCompat.getColor(mContext,R.color.lightGray))
                setTextColor(R.id.mCategory,ContextCompat.getColor(mContext,R.color.colorPrimaryDark))
            }else{
                setBackgroundColor(R.id.mCategory, 0)
                setTextColor(R.id.mCategory,Color.GRAY)
            }
        }
    }


}
package com.study.wanandroid.project.data

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author dengdai
 * @description
 */
class ProjectEntity(private var type : Int, var project : Project) : MultiItemEntity{

    companion object {
        const val LEFT = 1
        const val RIGHT = 2
    }

    override fun getItemType(): Int {
        return type
    }

}
package com.study.wanandroid.system.data

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author dengdai
 * @date 2020/2/20.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
class TopMenu : SectionEntity<SecondMenuRsp> {
    private var isMore:Boolean = false
    lateinit var children:List<SecondMenuRsp>
    constructor(isHeader:Boolean,header:String,isMore:Boolean,children:List<SecondMenuRsp>):super(isHeader,header){
        this.isMore = isMore
        this.children = children
    }
    constructor(t:SecondMenuRsp):super(t)
}
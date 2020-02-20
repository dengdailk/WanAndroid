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
    var isMore:Boolean = false
    lateinit var childrens:List<SecondMenuRsp>
    constructor(isHeader:Boolean,header:String,isMore:Boolean,childrens:List<SecondMenuRsp>):super(isHeader,header){
        this.isMore = isMore
        this.childrens = childrens
    }
    constructor(t:SecondMenuRsp):super(t)
}
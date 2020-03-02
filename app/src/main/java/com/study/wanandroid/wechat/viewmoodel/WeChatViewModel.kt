package com.study.wanandroid.wechat.viewmoodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.study.wanandroid.api.BaseResponse
import com.study.wanandroid.common.article.viewmodel.ArticleViewModel
import com.study.wanandroid.wechat.data.WeChatListRsp
import com.study.wanandroid.wechat.data.WeChatNameRsp
import com.study.wanandroid.wechat.data.WeChatRepository


/**
 * @author dengdai
 * @description
 */
class WeChatViewModel(application: Application) : ArticleViewModel<WeChatRepository>(application) {

    var mWeChatData: MutableLiveData<BaseResponse<List<WeChatNameRsp>>> = MutableLiveData()
    var mWeChatListData: MutableLiveData<BaseResponse<WeChatListRsp>> = MutableLiveData()

    fun getWeChat() {
        mRespository.getWeChatData(mWeChatData)
    }

    fun getWeChatList(uid: Int, page: Int) {
        mRespository.getWeChatListData(uid, page, mWeChatListData)
    }

}